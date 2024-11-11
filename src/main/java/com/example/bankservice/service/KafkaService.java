package com.example.bankservice.service;

import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.request.EventManagementSystemTransactionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Slf4j
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

        try {
            transactionService.executeTransaction(eventManagementSystemTransactionRequest.getAccountNumber(),
                    eventManagementSystemTransactionRequest.getPassword(),
                    eventManagementSystemTransactionRequest.getAmount(),
                    eventManagementSystemTransactionRequest.getTransactionType(),
                    eventManagementSystemTransactionRequest.getOrganizerBankAccountNumber());
        } catch(WrongAccountNumberException exception) {
            log.error(exception.getMessage());
            eventManagementSystemTransactionRequest.setReasonOfFail(exception.getMessage());
            kafkaMessageProducer.sendTransactionFailedMessageToFailedTransactionsTopic(eventManagementSystemTransactionRequest);
            System.out.println("Message sent to FailedTransactionsTopic: " + eventManagementSystemTransactionRequest);
            return;
        } catch (BadCredentialsException exception) {
            log.error(exception.getMessage());
            eventManagementSystemTransactionRequest.setReasonOfFail(exception.getMessage());
            kafkaMessageProducer.sendTransactionFailedMessageToFailedTransactionsTopic(eventManagementSystemTransactionRequest);
            return;
        }
        kafkaMessageProducer.sendTransactionCompletedMessageToCompletedTransactionsTopic(eventManagementSystemTransactionRequest);
        System.out.println("Message sent to CompletedTransactionsTopic: " + eventManagementSystemTransactionRequest);
    }
}
