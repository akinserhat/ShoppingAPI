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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final Clock clock;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .categoryId(product.getCategory().getId())
                        .stockAmount(product.getStockAmount())
                        .createdAt(product.getCreatedAt())
                        .build())
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
        product.setCreatedAt(getLocalDateTimeNow());
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

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}
