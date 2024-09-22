package com.example.bankservice.transaction;

import org.springframework.stereotype.Component;

@Component
public interface PaymentTransaction {
    void execute(String senderAccountNumber, double amount, String recipientAccountNumber);
}
