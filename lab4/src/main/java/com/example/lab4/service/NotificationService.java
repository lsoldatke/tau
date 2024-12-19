package com.example.lab4.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(Long recipientId, String content) {
        System.out.println("Notification sent to " + recipientId + ": " + content);
    }
}
