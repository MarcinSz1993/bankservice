package com.example.bankservice.exception;

public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException() {
        super("The client already exists!");
    }
}
