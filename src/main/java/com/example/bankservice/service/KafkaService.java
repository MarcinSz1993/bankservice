package com.example.bankservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaService {

    @KafkaListener(topics = "expectingPayments",groupId = "bank-service")
    public void consumeTransactionRequestMessage(Map<String,Object> message){
        System.out.println("Message from Kafka: " + message);

    }
}
