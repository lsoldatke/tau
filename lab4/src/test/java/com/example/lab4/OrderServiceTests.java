package com.example.lab4;

import com.example.lab4.enums.OrderStatus;
import com.example.lab4.enums.PaymentStatus;
import com.example.lab4.model.Order;
import com.example.lab4.model.Payment;
import com.example.lab4.repository.OrderRepository;
import com.example.lab4.service.InventoryService;
import com.example.lab4.service.NotificationService;
import com.example.lab4.service.OrderService;
import com.example.lab4.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {
    @Mock
    private InventoryService inventoryService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void successfullyCreateOrder() {
        Order order = new Order(1L, 10);

        Order savedOrder = orderService.createOrder(order);

        assertEquals(OrderStatus.PLACED, savedOrder.getStatus());
    }

    @Test
    public void productNotAvailable() {
        Order order = new Order(2L, 25);

        Order savedOrder = orderService.createOrder(order);

        assertEquals(OrderStatus.CANCELED, savedOrder.getStatus());
    }

    @Test
    public void paymentHasNotBeenProcessed() {
        Order order = new Order(3L, 15);

        Order savedOrder = orderService.createOrder(order);
        Payment payment = paymentService.getById(savedOrder.getPaymentId());

        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
        assertEquals(OrderStatus.CANCELED, savedOrder.getStatus());
    }

    @Test
    public void negativeTransactionAmount() {
        Order order = new Order(4L, -10);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrder(order);
        });

        assertEquals("Payment cannot be processed - negative amount not allowed", exception.getMessage());
    }
}
