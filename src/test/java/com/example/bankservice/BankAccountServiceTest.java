package com.example.bankservice;

import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.repository.BankAccountRepository;
import com.example.bankservice.service.BankAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {
    @Mock
    BankAccountRepository bankAccountRepository;
    @InjectMocks
    BankAccountService bankAccountService;
    @Test
    public void getAccountNumberTestIfGeneratedAccountNumberHasExactly9Numbers(){
        long accountNumber = 123456789;
        String expectedAccountNumber = String.valueOf(accountNumber);

        String actualAccountNumber = bankAccountService.generateAccountNumber();
        String expectedActualAccountNumber = String.valueOf(actualAccountNumber);

        Assertions.assertEquals(expectedAccountNumber.length(), expectedActualAccountNumber.length());
    }

    @Test
    public void getAccountNumberTestIfGeneratedAccountNumberHasIsNotNull(){
        String actualAccountNumber = bankAccountService.generateAccountNumber();

        Assertions.assertNotNull(actualAccountNumber);
    }

    @Test
    public void getAccountNumberTestIfGeneratedAccountNumberContainsNumbersOnly(){
        String actualAccountNumber = bankAccountService.generateAccountNumber();

        Assertions.assertTrue(actualAccountNumber.matches("\\d{9}"));
    }
    @Test
    public void checkBalanceTestWhenAccountNumberIsCorrect() throws Throwable {
        String accountNumber = "123456789";
        Optional<BankAccount> bankAccount = Optional.of(new BankAccount(1L,"123456789",1000,null,null));
        double expectedBalance = 1000;

        when(bankAccountRepository.findByAccountNumber(accountNumber)).thenReturn(bankAccount);

        double actualBalance = bankAccountService.checkBalance("123456789");

        Assertions.assertEquals(expectedBalance,actualBalance);

    }

    @Test
    public void checkBalanceTestWhenAccountNumberIsNotCorrect() {
        String accountNumber = "1234567899";
        Optional<BankAccount> bankAccount = Optional.empty();

        when(bankAccountRepository.findByAccountNumber(accountNumber)).thenReturn(bankAccount);

        Assertions.assertThrows(WrongAccountNumberException.class,()-> bankAccountService.checkBalance("1234567899"));

    }


}
