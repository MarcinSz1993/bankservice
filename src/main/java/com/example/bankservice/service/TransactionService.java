package com.example.bankservice.service;

import com.example.bankservice.exception.NotEnoughMoneyException;
import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.model.Transaction;
import com.example.bankservice.model.TransactionType;
import com.example.bankservice.repository.BankAccountRepository;
import com.example.bankservice.repository.TransactionRepository;
import com.example.bankservice.transaction.DepositTransaction;
import com.example.bankservice.transaction.OnlinePaymentTransaction;
import com.example.bankservice.transaction.WithdrawalTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final OnlinePaymentTransaction onlinePaymentTransaction;
    private final WithdrawalTransaction withdrawalTransaction;
    private final DepositTransaction depositTransaction;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Transaction executeTransaction(String senderAccountNumber,String password, double amount, TransactionType transactionType,String recipientAccountNumber) throws WrongAccountNumberException{
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(senderAccountNumber).orElseThrow(WrongAccountNumberException::new);
        if (!passwordEncoder.matches(password,bankAccount.getClient().getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        if (transactionType == TransactionType.ONLINE_PAYMENT) {
            onlinePaymentTransaction.execute(senderAccountNumber, amount, recipientAccountNumber);
        } else if (transactionType == TransactionType.DEPOSIT) {
            depositTransaction.execute(senderAccountNumber, amount);
        } else if (transactionType == TransactionType.WITHDRAWAL) {
            withdrawalTransaction.execute(senderAccountNumber, amount);
        }
        Transaction transaction = createTransaction(amount, transactionType, bankAccount);
        return transactionRepository.save(transaction);
    }


    public static Transaction createTransaction(double amount, TransactionType transactionType, BankAccount account) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTypeOfTransaction(transactionType);
        transaction.setDateOfTransaction(LocalDateTime.now());
        transaction.setBankAccount(account);
        return transaction;
    }

    public void validateEnoughMoney(double amount, BankAccount account) {
        if(amount > account.getBalance()){
            throw new NotEnoughMoneyException();
        }
    }
}
