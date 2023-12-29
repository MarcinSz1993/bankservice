package com.example.bankservice.repository;

import com.example.bankservice.model.BankAccount;
import com.example.bankservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
