package com.divij.orderservice.repo;

import com.divij.orderservice.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    private final Map<String, Order> data = new HashMap<>();

    public void save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        data.put(order.getOrderId(), order);
    }

    public Order getById(String orderId) {
        Order order = data.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }
        return order;
    }
}
