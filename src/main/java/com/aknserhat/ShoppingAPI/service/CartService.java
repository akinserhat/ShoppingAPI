package com.aknserhat.ShoppingAPI.service;

import com.aknserhat.ShoppingAPI.dto.AddToCartRequest;
import com.aknserhat.ShoppingAPI.dto.CartItemDto;
import com.aknserhat.ShoppingAPI.dto.ProductDto;
import com.aknserhat.ShoppingAPI.model.CartItem;
import com.aknserhat.ShoppingAPI.model.Product;
import com.aknserhat.ShoppingAPI.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public List<CartItemDto> getAllCartItems() {
        return cartRepository.findAll()
                .stream()
                .map(cartItems -> {
                    ProductDto product = productService.getProductById(cartItems.getProductId());
                    return CartItemDto.builder()
                            .cartId(cartItems.getId())
                            .productId(cartItems.getProductId())
                            .productName(product.getName())
                            .price(product.getPrice())
                            .quantity(cartItems.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public CartItemDto addToCart(AddToCartRequest addToCartRequest) {
        ProductDto productDto = productService.getProductById(addToCartRequest.getProductId());
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productDto.getId());
        cartItem.setQuantity(addToCartRequest.getQuantity());
        cartRepository.save(cartItem);

        return CartItemDto.builder()
                .cartId(cartItem.getId())
                .productId(cartItem.getProductId())
                .productName(productDto.getName())
                .quantity(cartItem.getQuantity())
                .price(productDto.getPrice())
                .build();
    }
}
