package com.example.bankservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

import java.util.Random;

@Validated
@SpringBootApplication
public class BankserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankserviceApplication.class, args);
		BankserviceApplication application = new BankserviceApplication();
		String generatedAccountNumber = application.generateAccountNumber();
		System.out.println("Generated Account Number: " + generatedAccountNumber);
	}

	public String generateAccountNumber() {
		Random random = new Random();
		long accountNumber = random.nextLong(100000000, 999999999);
		return String.valueOf(accountNumber);
	}



}
