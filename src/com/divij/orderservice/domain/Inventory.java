package com.divij.orderservice.domain;

public class Inventory {

    private final String productId;
    private int availableQty;
    private int reservedQty;

    public Inventory(String productId, int availableQty) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("productId must be not null or blank");
        }
        if (availableQty < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.productId = productId;
        this.availableQty = availableQty;
        this.reservedQty = 0;
    }

    //order placed
    public void reserve(int qty) {
        if (qty <= 0 || qty > availableQty) {
            throw new IllegalArgumentException("qty must be > 0 AND <= availableQty");
        }
        availableQty -= qty;
        reservedQty += qty;
    }

    //order cancelled
    public void release(int qty) {
        if (qty <= 0 || qty > reservedQty) {
            throw new IllegalArgumentException("invalid qty");
        }
        reservedQty -= qty;
        availableQty += qty;
    }

    //order shipped
    public void consume(int qty) {
        if (qty <= 0 || qty > reservedQty) {
            throw new IllegalArgumentException("qty must be greater than 0");
        }
        reservedQty -= qty;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public String getProductId() {
        return productId;
    }

    public int getReservedQty() {
        return reservedQty;
    }
}
