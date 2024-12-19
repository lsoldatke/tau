package com.example.lab4.model;

import com.example.lab4.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Long orderId;
    
    public Payment() {}
}
