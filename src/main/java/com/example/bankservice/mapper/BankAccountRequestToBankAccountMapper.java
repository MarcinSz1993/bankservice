package com.example.bankservice.mapper;

import com.example.bankservice.model.BankAccount;
import com.example.bankservice.request.BankAccountRequest;

public class BankAccountRequestToBankAccountMapper {

    public BankAccount bankAccountRequestToBankAccount (BankAccountRequest bankAccountRequest){
        return BankAccount.builder()
                .build();

    }
}
