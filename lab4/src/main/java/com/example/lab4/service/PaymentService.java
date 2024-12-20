package com.example.lab4.service;

import com.example.lab4.enums.PaymentStatus;
import com.example.lab4.model.Payment;
import com.example.lab4.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment getById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No payment with given ID"));
    }

    public Payment processPayment(Payment payment) {
        try {
            if (payment.getAmount() <= 0) {
                payment.setStatus(PaymentStatus.REJECTED);
                paymentRepository.save(payment);

                throw new IllegalArgumentException("Payment cannot be processed - negative amount not allowed");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }

        return paymentRepository.save(payment);
    }
}
