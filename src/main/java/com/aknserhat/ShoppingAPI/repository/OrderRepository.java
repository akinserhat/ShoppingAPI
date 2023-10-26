package com.aknserhat.ShoppingAPI.repository;

import com.aknserhat.ShoppingAPI.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
