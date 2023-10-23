package com.aknserhat.ShoppingAPI.service;

import com.aknserhat.ShoppingAPI.dto.CategoryDto;
import com.aknserhat.ShoppingAPI.dto.CategoryNameDto;
import com.aknserhat.ShoppingAPI.dto.CreateCategoryRequest;
import com.aknserhat.ShoppingAPI.model.Category;
import com.aknserhat.ShoppingAPI.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryNameDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> CategoryNameDto.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .collect(Collectors.toList());
    }
    //hata var düzelt
    public CategoryDto getCategoryDetailsById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> CategoryDto.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .products(category.getProducts())
                        .build())
                .orElseThrow(() -> new RuntimeException("HATAAAAAA!!!!!"));
    }

    public CategoryNameDto createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category();
        category.setName(createCategoryRequest.getName());
        categoryRepository.save(category);

        CategoryNameDto categoryNameDto = CategoryNameDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryNameDto;
    }
}
