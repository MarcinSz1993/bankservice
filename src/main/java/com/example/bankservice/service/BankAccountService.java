package com.example.bankservice.service;

import com.example.bankservice.exception.IncorrectPeselException;
import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.model.Client;
import com.example.bankservice.repository.BankAccountRepository;
import com.example.bankservice.repository.ClientRepository;
import com.example.bankservice.request.CreateBankAccountRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.function.Supplier;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class BankAccountService {
    BankAccount bankAccount;
    private final BankAccountRepository bankAccountRepository;

    private final ClientRepository clientRepository;

    public BankAccount createBankAccount(CreateBankAccountRequest request) {
        BankAccount bankAccount = new BankAccount();
        Client client = clientRepository.findByPesel(request.getPesel()).orElseThrow(IncorrectPeselException::new);

        bankAccount.setAccountNumber(generateAccountNumber());
        bankAccount.setBalance(0.0);
        bankAccount.setClient(client);
        bankAccount.setTransactions(Collections.emptyList());
        return bankAccountRepository.save(bankAccount);
    }

    public String generateAccountNumber(){
        long time = System.currentTimeMillis();
        return String.valueOf(time).substring(2, 12);
    }

    public double checkBalance(String accountNumber) throws Throwable {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow((Supplier<Throwable>) WrongAccountNumberException::new)
                .getBalance();
    }

    @Transactional
    public void deleteBankAccount(String accountNumber,String pesel) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber).orElseThrow(WrongAccountNumberException::new);
        if(bankAccount.getClient().getPesel().equals(pesel) && bankAccount.getBalance()==0){
            bankAccountRepository.delete(bankAccount);
        } else {
            throw new IncorrectPeselException();
        }
    }
}



