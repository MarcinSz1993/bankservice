package com.example.bankservice.request;

import com.example.bankservice.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataForTransferRequest {
    private String senderAccountNumber;
    private double amount;
    private String password;
    private TransactionType transactionType;
    private String recipientAccountNumber;
}
