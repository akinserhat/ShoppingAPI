package com.aknserhat.ShoppingAPI.controller;

import com.aknserhat.ShoppingAPI.dto.CategoryDto;
import com.aknserhat.ShoppingAPI.dto.CategoryNameDto;
import com.aknserhat.ShoppingAPI.dto.CreateCategoryRequest;
import com.aknserhat.ShoppingAPI.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryNameDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryDetailsById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryNameDto> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(createCategoryRequest));
    }

}
