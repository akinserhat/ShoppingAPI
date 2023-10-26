package com.aknserhat.ShoppingAPI.service;

import com.aknserhat.ShoppingAPI.dto.OrderDto;
import com.aknserhat.ShoppingAPI.dto.OrderRequest;
import com.aknserhat.ShoppingAPI.model.CartItem;
import com.aknserhat.ShoppingAPI.model.Order;
import com.aknserhat.ShoppingAPI.model.OrderStatus;
import com.aknserhat.ShoppingAPI.repository.CartRepository;
import com.aknserhat.ShoppingAPI.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductService productService;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .orderNumber(order.getOrderNumber())
                        .orderStatus(order.getOrderStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public OrderDto createOrder(OrderRequest orderRequest) {
        Long cartId = orderRequest.getCartId();
        List<CartItem> cartItems = cartRepository.findCartItemsById(cartId);
        List<Long> orderedProductIds = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            Long productId = cartItem.getProductId();
            int quantity = cartItem.getQuantity();
            productService.decreaseStock(productId, quantity);
            orderedProductIds.add(productId);
        }
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setOrderStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);

        return OrderDto.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID().toString();
    }
}
