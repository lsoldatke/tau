package com.example.lab4.model;

import com.example.lab4.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Long customerId;
    private Long productId;

    public Order() {
    }

    public Order(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }
}
