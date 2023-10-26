package com.aknserhat.ShoppingAPI.repository;

import com.aknserhat.ShoppingAPI.dto.CartItemDto;
import com.aknserhat.ShoppingAPI.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemsById(Long id);
}
