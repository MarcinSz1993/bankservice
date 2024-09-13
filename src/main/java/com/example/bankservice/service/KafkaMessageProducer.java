package com.example.bankservice.service;

import com.example.bankservice.request.EventManagementSystemTransactionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendTransactionCompletedMessageToCompletedTransactionsTopic(EventManagementSystemTransactionRequest eventManagementSystemTransactionRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String serializedEventManagementSystemTransactionRequest = objectMapper.writeValueAsString(eventManagementSystemTransactionRequest);
        kafkaTemplate.send("completedTransactions",serializedEventManagementSystemTransactionRequest);
    }
}
