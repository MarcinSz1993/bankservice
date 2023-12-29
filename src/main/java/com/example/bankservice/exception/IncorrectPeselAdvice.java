package com.example.bankservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IncorrectPeselAdvice {
    @ResponseBody
    @ExceptionHandler(IncorrectPeselException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String incorrectPesel(IncorrectPeselException ex){
        return ex.getMessage();
    }
}
