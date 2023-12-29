package com.example.bankservice.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BankAccountRequest {
    @NotNull
    @Size(message = "Correct PESEL should have exactly 11 numbers!",min = 11,max = 11)
    private String pesel;

}
