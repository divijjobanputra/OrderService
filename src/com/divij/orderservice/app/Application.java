package com.divij.orderservice.app;

import com.divij.orderservice.domain.Inventory;
import com.divij.orderservice.domain.Order;
import com.divij.orderservice.domain.Product;
import com.divij.orderservice.repo.InventoryRepository;
import com.divij.orderservice.repo.OrderRepository;
import com.divij.orderservice.repo.ProductRepository;
import com.divij.orderservice.service.CreateOrderCommand;
import com.divij.orderservice.service.CreateOrderItem;
import com.divij.orderservice.service.OrderService;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        InventoryRepository inventoryRepository = new InventoryRepository();
        OrderRepository orderRepository = new OrderRepository();

        Product p1 = new Product("P1", "Apple", 100);
        Product p2 = new Product("P2", "Banana", 200);

        Inventory i1 = new Inventory("P1", 5);
        Inventory i2 = new Inventory("P2", 1);

        productRepository.save(p1);
        productRepository.save(p2);

        inventoryRepository.save(i1);
        inventoryRepository.save(i2);

        OrderService orderService = new OrderService(productRepository, inventoryRepository, orderRepository);

        CreateOrderCommand cmd = new CreateOrderCommand(
                "O-1001",
                List.of(
                        new CreateOrderItem("P1", 2), // should reserve OK
                        new CreateOrderItem("P2", 2)  // should fail -> triggers rollback of P1
                )
        );

        System.out.println("=== BEFORE ===");
        printInventory(inventoryRepository, "P1");
        printInventory(inventoryRepository, "P2");

        // 6) Execute
        try {
            Order order = orderService.createOrder(cmd);
            System.out.println("\nOrder created: " + order.getOrderId());
        } catch (RuntimeException e) {
            System.out.println("\nOrder creation FAILED: " + e.getMessage());
        }

        // 7) Print after (P1 should be rolled back to available=5, reserved=0)
        System.out.println("\n=== AFTER ===");
        printInventory(inventoryRepository, "P1");
        printInventory(inventoryRepository, "P2");
    }

    private static void printInventory(InventoryRepository inventoryRepo, String productId) {
        Inventory inv = inventoryRepo.getByProductId(productId);

        // Adjust these getter names if your Inventory uses different ones
        System.out.println(
                "Inventory " + productId +
                        " | available=" + inv.getAvailableQty() +
                        " | reserved=" + inv.getReservedQty()
        );
    }
}
