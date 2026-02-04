package com.divij.orderservice.service;
import com.divij.orderservice.domain.Order;
import com.divij.orderservice.domain.OrderItem;
import com.divij.orderservice.repo.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final ProductRepository productRepo;
    private final InventoryRepository inventoryRepo;
    private final OrderRepository orderRepo;

    public OrderService(ProductRepository productRepo, InventoryRepository inventoryRepo, OrderRepository orderRepo){
        if(productRepo == null || inventoryRepo == null || orderRepo == null){
            throw new IllegalArgumentException("Repositories cannot be null");
        }
        this.inventoryRepo = inventoryRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }
}
