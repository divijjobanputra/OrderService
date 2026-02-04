package com.divij.orderservice.domain;

public class OrderItem {
    private final String productId;
    private final int qty;
    private final double unitPrice;
    private final double lineTotal;

    public OrderItem(String productId, int qty, double unitPrice) {
        if(productId == null || productId.isBlank()){
            throw new IllegalArgumentException("com.divij.orderservice.domain.Product Id cannot be null or blank");
        }
        if(qty <= 0){
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        if(unitPrice < 0){
            throw new IllegalArgumentException("Unit Price cannot be less than 0");
        }
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.lineTotal = this.unitPrice * this.qty;
    }

    public String getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getLineTotal() {
        return lineTotal;
    }
}
