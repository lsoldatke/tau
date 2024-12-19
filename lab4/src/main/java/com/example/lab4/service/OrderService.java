package com.example.lab4.service;

import com.example.lab4.model.Order;
import com.example.lab4.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
