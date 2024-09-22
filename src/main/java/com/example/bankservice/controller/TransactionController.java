package com.example.bankservice.controller;

import com.example.bankservice.model.Transaction;
import com.example.bankservice.request.DataForTransferRequest;
import com.example.bankservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PutMapping("/")
    public Transaction executeTransaction(@RequestBody DataForTransferRequest dataForTransferRequest){
        return transactionService.executeTransaction(dataForTransferRequest.getSenderAccountNumber(),
                dataForTransferRequest.getAmount(),
                dataForTransferRequest.getTransactionType(),
                dataForTransferRequest.getRecipientAccountNumber());
    }

}
