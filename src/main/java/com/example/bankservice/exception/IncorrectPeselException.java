package com.example.bankservice.exception;

public class IncorrectPeselException extends RuntimeException{
    public IncorrectPeselException() {
        super("Incorrect pesel!");
    }
}
