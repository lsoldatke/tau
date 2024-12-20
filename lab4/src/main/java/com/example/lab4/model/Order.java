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
    private Long paymentId;
    private Long productId;
    private int quantity;

    public Order() {
    }

    public Order(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
