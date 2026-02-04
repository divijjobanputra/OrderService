package com.divij.orderservice.service;

import java.util.List;

public class CreateOrderCommand {
    private final String orderId;
    private final List<CreateOrderItem> items;

    public CreateOrderCommand(String orderId, List<CreateOrderItem> items) {
        if(orderId == "null" || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or blank");
        }
        if(items.isEmpty() || items.contains(null) || items == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }
        this.orderId = orderId;
        this.items = List.copyOf(items);
    }

    public String getOrderId() {
        return orderId;
    }

    public List<CreateOrderItem> getItems() {
        return items;
    }
}