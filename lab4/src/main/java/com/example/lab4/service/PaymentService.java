package com.example.lab4.service;

import com.example.lab4.model.Payment;
import com.example.lab4.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(Payment payment) {
        try {
            if (payment.getAmount() <= 0) {
                throw new IllegalArgumentException("Payment cannot be processed - negative amount not allowed");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
        
        return paymentRepository.save(payment);
    }
}
