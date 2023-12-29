package com.example.bankservice.service;

import com.example.bankservice.exception.NotEnoughMoneyException;
import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.model.Transaction;
import com.example.bankservice.model.TransactionType;
import com.example.bankservice.repository.BankAccountRepository;
import com.example.bankservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final BankAccountRepository bankAccountRepository;


    public Transaction executeTransaction(String accountNumber, double amount, TransactionType transactionType){
        Optional<BankAccount> account = bankAccountRepository.findByAccountNumber(accountNumber);
        if(account.isEmpty()){
            throw new WrongAccountNumberException();
        }

        if(!transactionType.name().equals("DEPOSIT") && account.orElseThrow().getBalance() < amount)
        {
            throw new NotEnoughMoneyException();
        }

        Transaction transaction = createTransaction(amount, transactionType, account);
        validateEnoughMoney(amount, account,transactionType);
        updateAccountBalance(amount, transactionType, account);
        bankAccountRepository.save(account.get());

        return transactionRepository.save(transaction);
    }

    private static Transaction createTransaction(double amount, TransactionType transactionType, Optional<BankAccount> account) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTypeOfTransaction(transactionType);
        transaction.setDateOfTransaction(LocalDateTime.now());
        transaction.setBankAccount(account.orElseThrow());
        return transaction;
    }

    private static void updateAccountBalance(double amount, TransactionType transactionType, Optional<BankAccount> account) {
        double currentBalance = account.orElseThrow().getBalance();
        if (transactionType == TransactionType.DEPOSIT) {
            account.get().setBalance(currentBalance + amount);
        } else {
            account.get().setBalance(currentBalance - amount);
        }
    }

    public static void validateEnoughMoney(double amount, Optional<BankAccount> account,TransactionType transactionType) {
        if(!transactionType.name().equals("DEPOSIT") || amount > account.orElseThrow().getBalance()){
            throw new NotEnoughMoneyException();
        }
    }
}
