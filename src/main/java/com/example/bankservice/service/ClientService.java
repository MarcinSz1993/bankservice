package com.example.bankservice.service;

import com.example.bankservice.exception.ClientAlreadyExistsException;
import com.example.bankservice.exception.NotEnoughMoneyException;
import com.example.bankservice.request.ClientRequest;
import com.example.bankservice.mapper.ClientDtoToClientMapper;
import com.example.bankservice.model.Client;
import com.example.bankservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    public Client createClient(ClientRequest clientRequest){
        if(alreadyExists(clientRequest.getPesel())){
            throw new ClientAlreadyExistsException();
        }
        Client client = ClientDtoToClientMapper.clientDtoToClient(clientRequest);
        client.setBankAccounts(Collections.emptyList());
        return clientRepository.save(client);
    }

    public boolean alreadyExists(String pesel){
        Optional<Client> findingByPesel = clientRepository.findByPesel(pesel);
        return findingByPesel.isPresent();
    }
}
