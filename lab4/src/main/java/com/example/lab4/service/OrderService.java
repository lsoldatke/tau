package com.example.lab4.service;

import com.example.lab4.enums.OrderStatus;
import com.example.lab4.enums.PaymentStatus;
import com.example.lab4.model.Order;
import com.example.lab4.model.Payment;
import com.example.lab4.model.Product;
import com.example.lab4.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final InventoryService inventoryService;

    public OrderService(OrderRepository orderRepository, PaymentService paymentService, InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
    }

    public Order createOrder(Order order) {
        try {
            order.setStatus(OrderStatus.PLACED);

            Order savedOrder = orderRepository.save(order);
            Product orderedProduct = inventoryService.getById(savedOrder.getProductId());
            Payment payment = paymentService.processPayment(new Payment(orderedProduct.getPrice(), PaymentStatus.PENDING, savedOrder.getId()));

            if (payment.getStatus() == PaymentStatus.APPROVED) savedOrder.setStatus(OrderStatus.PAID);

            return orderRepository.save(savedOrder);
        } catch (IllegalArgumentException e) {
            System.out.println("Error processing payment: " + e.getMessage());
        }
    }
}
