package com.example.bankservice.transaction;

import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DepositTransaction implements WithdrawalOrDepositTransaction {
    private final BankAccountRepository bankAccountRepository;
    @Transactional
    @Override
    public void execute(String ownerAccount, double amount) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(ownerAccount).orElseThrow(WrongAccountNumberException::new);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }
}
