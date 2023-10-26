package com.aknserhat.ShoppingAPI.service;

import com.aknserhat.ShoppingAPI.dto.CategoryDto;
import com.aknserhat.ShoppingAPI.dto.CreateProductRequest;
import com.aknserhat.ShoppingAPI.dto.ProductDto;
import com.aknserhat.ShoppingAPI.model.Category;
import com.aknserhat.ShoppingAPI.model.Product;
import com.aknserhat.ShoppingAPI.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    CategoryDto categoryDto = categoryService.getCategoryDetailsById(product.getCategory().getId());
                    return ProductDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .categoryId(categoryDto.getId())
                            .stockAmount(product.getStockAmount())
                            .createdAt(product.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .categoryId(product.getCategory().getId())
                        .stockAmount(product.getStockAmount())
                        .createdAt(product.getCreatedAt())
                        .build())
                .orElseThrow();
    }

    public ProductDto createProduct(CreateProductRequest createProductRequest) {
        CategoryDto categoryDto = categoryService.getCategoryDetailsById(createProductRequest.getCategoryId());
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());

        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        product.setCategory(category);
        product.setStockAmount(createProductRequest.getStockAmount());
        product.setCreatedAt(LocalDateTime.now());
        productRepository.save(product);

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .stockAmount(product.getStockAmount())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public void decreaseStock(Long productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            int currentStock = product.getStockAmount();
            if (currentStock >= quantity) {
                product.setStockAmount(currentStock - quantity);
                productRepository.save(product);
            } else {
                throw new IllegalArgumentException("Insufficient stock for product: " + productId);
            }
        } else {
            throw new NoSuchElementException("Product not found with ID: " + productId);
        }
    }

    public void increaseStock(Long productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            int currentStock = product.getStockAmount();
            product.setStockAmount(currentStock + quantity);
            productRepository.save(product);
        } else {
            throw new NoSuchElementException("Product not found with ID: " + productId);
        }
    }
}
