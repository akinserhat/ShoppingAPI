package com.aknserhat.ShoppingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private Long categoryId;
    private int stockAmount;
    private LocalDateTime createdAt;
}
