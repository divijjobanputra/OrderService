package com.divij.orderservice.service;
import com.divij.orderservice.domain.Inventory;
import com.divij.orderservice.domain.Order;
import com.divij.orderservice.domain.OrderItem;
import com.divij.orderservice.domain.Product;
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

    public Order createOrder(CreateOrderCommand cmd) {
        if (cmd == null) {
            throw new IllegalArgumentException("cmd cannot be null");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();

        try {
            for (CreateOrderItem item : cmd.getItems()) {
                String productId = item.getProductId();
                int qty = item.getQty();

                Product product = productRepo.getById(productId);
                Inventory inventory = inventoryRepo.getByProductId(productId);

                // reserve first (this is the first state change)
                inventory.reserve(qty);

                // record only AFTER reserve succeeds (so rollback is correct)
                reservations.add(new Reservation(inventory, qty));

                // snapshot price at order-creation time
                OrderItem orderItem = new OrderItem(productId, qty, product.getProductPrice());
                orderItems.add(orderItem);
            }

            Order order = new Order(cmd.getOrderId(), orderItems);
            orderRepo.save(order);
            return order;

        } catch (RuntimeException e) {
            // rollback: undo only what succeeded so far
            for (int i = reservations.size() - 1; i >= 0; i--) {
                Reservation r = reservations.get(i);
                try {
                    r.inventory.release(r.qty);
                } catch (RuntimeException ignored) {
                    // best-effort rollback; don't hide original error
                }
            }
            throw e;
        }
    }


    private static class Reservation{
        private final Inventory inventory;
        private final int qty;

        public Reservation(Inventory inventory, int qty){
            this.inventory = inventory;
            this.qty = qty;
        }
    }
}


