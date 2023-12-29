package com.example.bankservice.mapper;

import com.example.bankservice.model.Client;
import com.example.bankservice.request.ClientRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Getter
public class ClientMapper {

    public static Client clientRequestToClientMapper(ClientRequest clientRequest){
        return Client.builder()
                .id(clientRequest.getId())
                .name(clientRequest.getName())
                .surname(clientRequest.getSurname())
                .pesel(clientRequest.getPesel())
                .bankAccounts(Collections.emptyList())
                .build();
    }
}
