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
public class WithdrawalTransaction implements WithdrawalOrDepositTransaction {
    private final BankAccountRepository bankAccountRepository;

    @Override
    @Transactional
    public void execute(String ownerAccount, double amount) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(ownerAccount).orElseThrow(WrongAccountNumberException::new);
        if(bankAccount.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }
}
