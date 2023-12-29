package com.example.bankservice.controller;

import com.example.bankservice.model.Transaction;
import com.example.bankservice.model.TransactionType;
import com.example.bankservice.request.DataForTransferRequest;
import com.example.bankservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/")
    public Transaction makeTransaction(@RequestBody Map<String,Object> requestBody){

        String accountNumber = (String) requestBody.get("accountNumber");
        double amount = (double) requestBody.get("amount");
        String transactionTypeStr = (String) requestBody.get("transactionType");
        TransactionType transactionType = TransactionType.valueOf(transactionTypeStr);

        return transactionService.executeTransaction(accountNumber,amount,transactionType);
    }

    @PostMapping("/roomreservationtransaction")
    public Transaction executeBankTransaction(@RequestBody DataForTransferRequest dataForTransferRequest){
        return transactionService.executeTransaction(dataForTransferRequest.getAccountNumber(),
                dataForTransferRequest.getAmount(),
                TransactionType.valueOf(dataForTransferRequest.getTransactionType()));
    }

    /*@PostMapping("/transaction")
    public Transaction executeBankTransaction(@RequestParam String accountNumber,
                                              @RequestParam double amount,
                                              @RequestParam String transactionType){
        return transactionService.executeTransaction(accountNumber,
                amount,
                TransactionType.valueOf(transactionType));
    }*/
}
