package com.example.bankservice.exception;

public class WrongAccountNumberException extends RuntimeException {
    public WrongAccountNumberException() {
        super("Wrong account number!");
    }
}
