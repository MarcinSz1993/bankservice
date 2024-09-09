package com.example.bankservice.service;

import com.example.bankservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;
    @Override
    public UserDetails loadUserByUsername(String pesel) throws UsernameNotFoundException {
        return clientRepository.findByPesel(pesel).orElseThrow();
    }
}
