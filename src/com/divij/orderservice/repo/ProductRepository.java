package com.divij.orderservice.repo;

import com.divij.orderservice.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private final Map<String, Product> data = new HashMap<>();

    public Product getById(String productId) {
        Product product = data.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found: " + productId);
        }
        return product;
    }

    public void save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        data.put(product.getProductId(), product);
    }
}
