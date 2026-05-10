package com.carrepair.paymentservice.service;

import com.carrepair.paymentservice.dto.PaymentRequest;
import com.carrepair.paymentservice.model.Payment;
import com.carrepair.paymentservice.repository.PaymentRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final PaymentRepository paymentRepository;

    public PaymentService(KafkaTemplate<String, String> kafkaTemplate, PaymentRepository paymentRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.paymentRepository = paymentRepository;
    }

    public String processPayment(PaymentRequest request) {
        String method = request.method().toUpperCase();
        if (!method.equals("STRIPE") && !method.equals("PAYPAL")) {
            throw new IllegalArgumentException("Supported methods: STRIPE, PAYPAL");
        }
        Payment payment = new Payment();
        payment.setOrderId(request.orderId());
        payment.setAmount(request.amount());
        payment.setMethod(method);
        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);

        String message = "Payment successful via " + method + " for order " + request.orderId() + " amount=" + request.amount();
        kafkaTemplate.send("notifications", message);
        return message;
    }
}
