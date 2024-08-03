package com.example.bankservice.controller;

import com.example.bankservice.model.Transaction;
import com.example.bankservice.request.DataForTransferRequest;
import com.example.bankservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PutMapping("/")
    public Transaction executeTransaction(@RequestBody DataForTransferRequest dataForTransferRequest){
        return transactionService.executeTransaction(dataForTransferRequest.getAccountNumber(),
                dataForTransferRequest.getAmount(),
                dataForTransferRequest.getTransactionType());
    }
}
