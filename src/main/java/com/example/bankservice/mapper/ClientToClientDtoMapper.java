package com.example.bankservice.mapper;

import com.example.bankservice.request.ClientRequest;
import com.example.bankservice.model.Client;

public class ClientToClientDtoMapper {

    public static ClientRequest clientToClientDto(Client client){
        return ClientRequest.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .pesel(client.getPesel())
                .build();
    }
}
