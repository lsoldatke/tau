package com.example.lab4.service;

import com.example.lab4.enums.OrderStatus;
import com.example.lab4.enums.PaymentStatus;
import com.example.lab4.model.Order;
import com.example.lab4.model.Payment;
import com.example.lab4.model.Product;
import com.example.lab4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;

    @Autowired
    public OrderService(OrderRepository orderRepository, PaymentService paymentService, InventoryService inventoryService, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
    }

    public Order createOrder(Order order) {
        try {
            Order savedOrder = orderRepository.save(order);
            Product orderedProduct = inventoryService.getById(savedOrder.getProductId());
            int productAvailability = inventoryService.checkAvailability(orderedProduct.getId());

            if (productAvailability < savedOrder.getQuantity()) {
                savedOrder.setStatus(OrderStatus.CANCELED);
                return orderRepository.save(savedOrder);
            }

            savedOrder.setStatus(OrderStatus.PLACED);

            Payment payment = paymentService.processPayment(new Payment(orderedProduct.getPrice(), savedOrder.getId()));
            savedOrder.setPaymentId(payment.getId());

            if (payment.getStatus() == PaymentStatus.APPROVED) savedOrder.setStatus(OrderStatus.PAID);

            inventoryService.setQuantity(savedOrder.getProductId(), productAvailability - savedOrder.getQuantity());
            notificationService.sendNotification("Your order with ID: " + savedOrder.getId() + " has been placed successfully.");

            return orderRepository.save(savedOrder);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
