package com.example.bankservice.request;

import com.example.bankservice.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventManagementSystemTransactionRequest {
    private String accountNumber;
    private double amount;
    private TransactionType transactionType;
    private Long eventId;
    private Long userId;
}