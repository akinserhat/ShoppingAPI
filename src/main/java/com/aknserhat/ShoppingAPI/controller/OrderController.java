package com.aknserhat.ShoppingAPI.controller;

import com.aknserhat.ShoppingAPI.dto.CartItemDto;
import com.aknserhat.ShoppingAPI.dto.OrderDto;
import com.aknserhat.ShoppingAPI.dto.OrderRequest;
import com.aknserhat.ShoppingAPI.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
    }
}
