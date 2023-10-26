package com.aknserhat.ShoppingAPI.controller;

import com.aknserhat.ShoppingAPI.dto.AddToCartRequest;
import com.aknserhat.ShoppingAPI.dto.CartItemDto;
import com.aknserhat.ShoppingAPI.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
        return ResponseEntity.ok(cartService.getAllCartItems());
    }

    @PostMapping
    public ResponseEntity<CartItemDto> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        CartItemDto cartItem = cartService.addToCart(addToCartRequest);
        return ResponseEntity.ok(cartItem);
    }
}
