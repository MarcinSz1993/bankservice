package com.example.bankservice.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateBankAccountRequest {
    @NotNull
    @Size(message = "Correct PESEL should have exactly 11 numbers!",min = 11,max = 11)
    private String pesel;

}
