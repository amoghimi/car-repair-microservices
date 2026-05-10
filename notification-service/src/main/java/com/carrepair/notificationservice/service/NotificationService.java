package com.carrepair.notificationservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "notifications", groupId = "notification-service")
    public void consume(String message) {
        System.out.println("[EMAIL-SIMULATION] Sending notification: " + message);
    }

    public String ping() {
        return "Notification service is running";
    }
}
