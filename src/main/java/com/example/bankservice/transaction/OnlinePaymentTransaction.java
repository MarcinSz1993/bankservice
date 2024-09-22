package com.example.bankservice.transaction;

import com.example.bankservice.exception.NotEnoughMoneyException;
import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OnlinePaymentTransaction implements PaymentTransaction{
    private final BankAccountRepository bankAccountRepository;

    @Override
    @Transactional
    public void execute(String senderAccountNumber, double amount, String recipientAccountNumber) {

        BankAccount senderBankAccount = bankAccountRepository.findByAccountNumber(senderAccountNumber).orElseThrow(WrongAccountNumberException::new);
        BankAccount recipientBankAccount = bankAccountRepository.findByAccountNumber(recipientAccountNumber).orElseThrow(WrongAccountNumberException::new);
        if(senderBankAccount.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        }

        senderBankAccount.setBalance(senderBankAccount.getBalance() - amount);
        recipientBankAccount.setBalance(recipientBankAccount.getBalance() + amount);

        bankAccountRepository.save(senderBankAccount);
        bankAccountRepository.save(recipientBankAccount);
    }
}
