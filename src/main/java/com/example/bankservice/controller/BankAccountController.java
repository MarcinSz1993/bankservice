package com.example.bankservice.controller;

import com.example.bankservice.model.BankAccount;
import com.example.bankservice.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    @PostMapping("/")
    public BankAccount createAccount(@RequestParam String pesel) {
        return bankAccountService.createBankAccount(pesel);
    }
    @GetMapping("/balance")
    public double checkBalance(@RequestParam String accountNumber) throws Throwable {
        return bankAccountService.checkBalance(accountNumber);
    }
}
