package com.example.lab4;

import com.example.lab4.service.OrderService;
import com.example.lab4.service.PaymentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private PaymentService paymentService;
}
