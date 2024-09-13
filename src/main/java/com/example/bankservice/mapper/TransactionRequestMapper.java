package com.example.bankservice.mapper;

import com.example.bankservice.request.EventManagementSystemTransactionRequest;
import com.example.bankservice.request.TransactionRequest;

public class TransactionRequestMapper {
    public static TransactionRequest convertEventManagementSystemRequestTransactionToRequestTransaction
            (EventManagementSystemTransactionRequest eventManagementSystemTransactionRequest)
    {
        return TransactionRequest.builder()
                .accountNumber(eventManagementSystemTransactionRequest.getAccountNumber())
                .amount(eventManagementSystemTransactionRequest.getAmount())
                .transactionType(eventManagementSystemTransactionRequest.getTransactionType())
                .build();
    }
}
