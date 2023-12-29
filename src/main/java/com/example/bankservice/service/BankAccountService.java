package com.example.bankservice.service;

import com.example.bankservice.exception.IncorrectPeselException;
import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.model.Client;
import com.example.bankservice.repository.ClientRepository;
import com.example.bankservice.request.BankAccountRequest;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.repository.BankAccountRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class BankAccountService {
    BankAccount bankAccount;
    private final BankAccountRepository bankAccountRepository;

    private final ClientRepository clientRepository;

    public BankAccount createBankAccount(String pesel) {
        BankAccount bankAccount = new BankAccount();
        Optional<Client> client = clientRepository.findByPesel(pesel);
        if(client.isEmpty()){
            throw new IncorrectPeselException();
        }
        bankAccount.setAccountNumber(generateAccountNumber());
        bankAccount.setBalance(0.0);
        bankAccount.setClient(client.get());
        bankAccount.setTransactions(Collections.emptyList());
        return bankAccountRepository.save(bankAccount);
    }

    public String generateAccountNumber(){
        Random random = new Random();
        long accountNumber = random.nextLong(100000000, 999999999);
        return String.valueOf(accountNumber);
    }

    public double checkBalance(String accountNumber) throws Throwable {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow((Supplier<Throwable>) WrongAccountNumberException::new)
                .getBalance();
    }

}



