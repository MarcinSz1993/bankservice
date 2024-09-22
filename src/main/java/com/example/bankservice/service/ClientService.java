package com.example.bankservice.service;

import com.example.bankservice.exception.ClientAlreadyExistsException;
import com.example.bankservice.exception.WrongAccountNumberException;
import com.example.bankservice.mapper.ClientDtoToClientMapper;
import com.example.bankservice.model.BankAccount;
import com.example.bankservice.model.Client;
import com.example.bankservice.repository.BankAccountRepository;
import com.example.bankservice.repository.ClientRepository;
import com.example.bankservice.request.ClientRequest;
import com.example.bankservice.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BankAccountRepository bankAccountRepository;

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

    public String verify(LoginRequest loginRequest) {

        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(loginRequest.getAccountNumber()).orElseThrow(WrongAccountNumberException::new);
        Client client = bankAccount.getClient();
        log.info("Authenticating user with PESEL: {} and password: {}", client.getPesel(), loginRequest.getPassword());

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(client.getPesel(), loginRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(client.getPesel());
        }
        return "fail";
    }
}
