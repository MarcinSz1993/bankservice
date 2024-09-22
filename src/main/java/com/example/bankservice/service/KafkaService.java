package com.example.bankservice.service;

import com.example.bankservice.request.EventManagementSystemTransactionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final TransactionService transactionService;
    private final KafkaMessageProducer kafkaMessageProducer;

    @KafkaListener(topics = "expectingPayments",groupId = "bank-service")
    public void consumeTransactionRequestMessage(String message) throws JsonProcessingException {
        System.out.println("Message from Kafka: " + message);
        ObjectMapper mapper = new ObjectMapper();
        EventManagementSystemTransactionRequest eventManagementSystemTransactionRequest = mapper.readValue(message, EventManagementSystemTransactionRequest.class);
        System.out.println("Converted message: " + eventManagementSystemTransactionRequest);
        transactionService.executeTransaction(eventManagementSystemTransactionRequest.getAccountNumber(),
                eventManagementSystemTransactionRequest.getAmount(),
                eventManagementSystemTransactionRequest.getTransactionType(),
                eventManagementSystemTransactionRequest.getRecipientAccountNumber());
        kafkaMessageProducer.sendTransactionCompletedMessageToCompletedTransactionsTopic(eventManagementSystemTransactionRequest);
        System.out.println("Message sent: " + eventManagementSystemTransactionRequest);
    }
}
