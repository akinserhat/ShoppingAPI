package com.aknserhat.ShoppingAPI.controller;

import com.aknserhat.ShoppingAPI.dto.CreateProductRequest;
import com.aknserhat.ShoppingAPI.dto.ProductDto;
import com.aknserhat.ShoppingAPI.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductsById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(createProductRequest));
    }
}
