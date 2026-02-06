package com.divij.orderservice.repo;

import com.divij.orderservice.domain.Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryRepository {

    private final Map<String, Inventory> data = new HashMap<>();

    public Inventory getByProductId(String productId) {
        Inventory inventory = data.get(productId);
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory not found for product: " + productId);
        }
        return inventory;
    }

    public void save(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory cannot be null");
        }
        data.put(inventory.getProductId(), inventory);
    }
}
