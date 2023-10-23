package com.aknserhat.ShoppingAPI.repository;

import com.aknserhat.ShoppingAPI.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
