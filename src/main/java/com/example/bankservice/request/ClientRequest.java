package com.example.bankservice.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    private Long id;
    @NotNull(message = "This field cannot be empty!")
    private String name;

    @NotNull(message = "This field cannot be empty!")
    private String surname;

    @NotNull
    private String password;

    @NotNull(message = "This field cannot be empty!")
    @Size(message = "Correct number should have exactly 11 numbers!",min = 11,max = 11)
    private String pesel;
}
