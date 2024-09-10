package com.example.bankservice.controller;

import com.example.bankservice.request.ClientRequest;
import com.example.bankservice.model.Client;
import com.example.bankservice.request.LoginRequest;
import com.example.bankservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    @PostMapping("/")
    public Client create(@RequestBody @Validated ClientRequest clientRequest){
        return clientService.createClient(clientRequest);
    }

    @PostMapping("/login")
    public String login (@RequestBody @Validated LoginRequest loginRequest){
        return clientService.verify(loginRequest);
    }
}
