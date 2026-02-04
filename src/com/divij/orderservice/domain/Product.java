package com.divij.orderservice.domain;

public class Product {
    private final String productId;
    private final String productName;
    private double productPrice;

    public Product(String productId, String productName, double productPrice) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("com.divij.orderservice.domain.Product id is invalid");
        }
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("com.divij.orderservice.domain.Product name is invalid");
        }
        if (productPrice < 0) {
            throw new IllegalArgumentException("com.divij.orderservice.domain.Product price is invalid");
        }
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void changeProductPrice(double newProductPrice){
        if(newProductPrice<0){
            throw new IllegalArgumentException("com.divij.orderservice.domain.Product price is invalid");
        }
        this.productPrice = newProductPrice;
    }
}