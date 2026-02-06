package com.divij.orderservice.service;

public class CreateOrderItem {
    private final String productId;
    private final int qty;

    public CreateOrderItem(String productId, int qty) {
        if(productId == null || productId.isBlank() || productId.trim().length() == 0) {
            throw new IllegalArgumentException("Product id is invalid");
        }
        if(qty<=0){
            throw new IllegalArgumentException("qty is invalid");
        }
        this.productId = productId;
        this.qty = qty;
    }

    public String getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }
}
