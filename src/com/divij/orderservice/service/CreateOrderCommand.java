package com.divij.orderservice.service;

import java.util.List;

public class CreateOrderCommand {
    private final String orderId;
    private final List<CreateOrderItem> items;

    public CreateOrderCommand(String orderId, List<CreateOrderItem> items) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or blank");
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be null or empty");
        }

        // manual null check instead of contains(null)
        for (CreateOrderItem item : items) {
            if (item == null) {
                throw new IllegalArgumentException("Order item cannot be null");
            }
        }
        this.orderId = orderId;
        this.items = List.copyOf(items);
    }

    public String getOrderId(){
        return orderId;
    }

    public List<CreateOrderItem> getItems() {
        return items;
    }
}