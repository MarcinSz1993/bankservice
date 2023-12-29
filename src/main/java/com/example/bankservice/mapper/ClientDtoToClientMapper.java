package com.example.bankservice.mapper;

import com.example.bankservice.request.ClientRequest;
import com.example.bankservice.model.Client;

public class ClientDtoToClientMapper {
    public static Client clientDtoToClient(ClientRequest clientRequest){
        return Client.builder()
                .id(clientRequest.getId())
                .name(clientRequest.getName())
                .surname(clientRequest.getSurname())
                .pesel(clientRequest.getPesel())
                .build();
    }
}
