package com.example.bankservice.controller;

import com.example.bankservice.model.BankAccount;
import com.example.bankservice.request.CreateBankAccountRequest;
import com.example.bankservice.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    @PostMapping("/")
    public BankAccount createAccount(@RequestBody CreateBankAccountRequest request) {
        return bankAccountService.createBankAccount(request);
    }
    @GetMapping("/balance")
    public double checkBalance(@RequestParam String accountNumber) throws Throwable {
        return bankAccountService.checkBalance(accountNumber);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteAccount(@RequestParam String accountNumber,
                                                @RequestParam String pesel){
        bankAccountService.deleteBankAccount(accountNumber, pesel);
        return ResponseEntity.ok().body("Account with number " + accountNumber + " deleted.");
    }
}
