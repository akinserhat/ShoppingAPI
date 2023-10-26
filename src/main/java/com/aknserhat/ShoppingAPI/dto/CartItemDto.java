package com.aknserhat.ShoppingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private Long cartId;
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}
