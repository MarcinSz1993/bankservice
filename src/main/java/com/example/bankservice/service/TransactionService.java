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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public Transaction executeTransaction(String accountNumber, double amount, TransactionType transactionType){
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber).orElseThrow(WrongAccountNumberException::new);

        if(!transactionType.name().equals("DEPOSIT") && account.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        }

        Transaction transaction = createTransaction(amount, transactionType, account);
        updateAccountBalance(amount, transactionType, account);
        bankAccountRepository.save(account);
        return transactionRepository.save(transaction);
    }

    private static Transaction createTransaction(double amount, TransactionType transactionType, BankAccount account) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTypeOfTransaction(transactionType);
        transaction.setDateOfTransaction(LocalDateTime.now());
        transaction.setBankAccount(account);
        return transaction;
    }

    private void updateAccountBalance(double amount, TransactionType transactionType, BankAccount account) {
        double currentBalance = account.getBalance();
        if (transactionType == TransactionType.DEPOSIT) {
            account.setBalance(currentBalance + amount);
        } else {
            account.setBalance(currentBalance - amount);
        }
    }

    public void validateEnoughMoney(double amount, BankAccount account) {
        if(amount > account.getBalance()){
            throw new NotEnoughMoneyException();
        }
    }
}
