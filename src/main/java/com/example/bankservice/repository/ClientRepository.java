package com.example.bankservice.repository;

import com.example.bankservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
 Optional<Client> findByPesel(String pesel);
}
