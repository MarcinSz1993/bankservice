package com.example.bankservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@Validated
@SpringBootApplication
public class BankserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankserviceApplication.class, args);
    }
}
