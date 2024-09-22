package com.example.bankservice.transaction;

public interface WithdrawalOrDepositTransaction {
    void execute(String ownerAccount,double amount);
}
