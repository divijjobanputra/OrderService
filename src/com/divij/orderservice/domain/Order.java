package com.divij.orderservice.domain;

import java.util.List;

public class Order {
    private final String orderId;
    private final List<OrderItem> items;
    private OrderStatus status;
    private double totalAmount;

    public Order(String orderId, List<OrderItem> items) {
        if(orderId == null || orderId.isBlank()){
            throw new IllegalArgumentException("id cannot be null or blank");
        }
        if(items == null || items.isEmpty() || items.contains(null)){
            throw new IllegalArgumentException("Order items cannot be null or empty");
        }
        this.orderId = orderId;
        this.items = List.copyOf(items);
        status = OrderStatus.CREATED;
        double sum = 0;
        for(OrderItem item : items){
            sum += item.getLineTotal();
        }
        this.totalAmount = sum;
    }

    public void cancel(){
        if(status==OrderStatus.CANCELLED){
            throw new IllegalStateException("Order already cancelled");
        }
        else {
            status = OrderStatus.CANCELLED;
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
