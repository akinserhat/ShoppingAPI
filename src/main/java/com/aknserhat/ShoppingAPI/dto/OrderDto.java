package com.aknserhat.ShoppingAPI.dto;

import com.aknserhat.ShoppingAPI.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String orderNumber;
    private OrderStatus orderStatus;
}
