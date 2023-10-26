package com.aknserhat.ShoppingAPI.dto;

import com.aknserhat.ShoppingAPI.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {
    private String name;
    private List<ProductDto> products = new ArrayList<>();
}
