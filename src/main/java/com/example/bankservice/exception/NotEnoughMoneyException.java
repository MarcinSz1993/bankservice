package com.example.bankservice.exception;

public class NotEnoughMoneyException extends RuntimeException{

    public NotEnoughMoneyException() {
        super("You don't have enough money to do it!");
    }
}
