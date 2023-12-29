package com.example.bankservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "transaction")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date_of_transaction")
    private LocalDateTime dateOfTransaction;

    @Column(name = "type_of_transaction")
    @Enumerated(EnumType.STRING)
    private TransactionType typeOfTransaction;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    @JsonIgnore
    private BankAccount bankAccount;

}
