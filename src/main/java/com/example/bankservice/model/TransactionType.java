package com.example.bankservice.model;

import lombok.Getter;

@Getter
public enum TransactionType {
    WITHDRAWAL,
    DEPOSIT,
    ONLINE_PAYMENT
}
