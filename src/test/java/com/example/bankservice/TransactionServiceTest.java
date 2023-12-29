package com.example.bankservice;

import com.example.bankservice.exception.NotEnoughMoneyException;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.model.Transaction;
import com.example.bankservice.model.TransactionType;
import com.example.bankservice.repository.BankAccountRepository;
import com.example.bankservice.repository.TransactionRepository;
import com.example.bankservice.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    BankAccountRepository bankAccountRepository;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    @Test
    public void validateEnoughMoneyTestWhenAmountIsBiggerThanBalance(){

        double amount = 600;
        Optional<BankAccount> bankAccount = Optional.of(new BankAccount(1L, "123456789", 500, null, null));

        TransactionType transactionType = TransactionType.valueOf("DEPOSIT");

        Assertions.assertThrows(NotEnoughMoneyException.class,()->TransactionService.validateEnoughMoney(amount,bankAccount,transactionType));
    }

    @Test
    public void executeTransaction(){
        String accountNumber = "123456789";
        double amount = 200;
        TransactionType transactionType = TransactionType.valueOf("DEPOSIT");
        BankAccount bankAccount = new BankAccount(1L, "123456789", 1500, null, Collections.emptyList());
        Transaction expectedTransaction = new Transaction(1L,1700,LocalDateTime.now(),transactionType,bankAccount);


        when(bankAccountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(bankAccount));

        when(transactionRepository.save(any(Transaction.class))).thenReturn(expectedTransaction);

        Transaction transaction = transactionService.executeTransaction(accountNumber, amount, transactionType);

        verify(transactionRepository, times(1)).save(any(Transaction.class));

        Assertions.assertNotNull(transaction);

    }
}
