package com.example.bankservice.service;

import com.example.bankservice.exception.ClientAlreadyExistsException;
import com.example.bankservice.mapper.ClientDtoToClientMapper;
import com.example.bankservice.model.Client;
import com.example.bankservice.repository.ClientRepository;
import com.example.bankservice.request.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final ClientRepository clientRepository;

    public Client createClient(ClientRequest clientRequest) {
        if (alreadyExists(clientRequest.getPesel())) {
            throw new ClientAlreadyExistsException();
        }
        Client client = ClientDtoToClientMapper.clientDtoToClient(clientRequest);
        client.setPassword(passwordEncoder.encode(clientRequest.getPassword()));
        client.setBankAccounts(Collections.emptyList());
        return clientRepository.save(client);
    }

    public boolean alreadyExists(String pesel) {
        Optional<Client> findingByPesel = clientRepository.findByPesel(pesel);
        return findingByPesel.isPresent();
    }

    public String verify(Client client) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(client.getUsername(), client.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(client.getPesel());
        }
        return "fail";
    }
}
