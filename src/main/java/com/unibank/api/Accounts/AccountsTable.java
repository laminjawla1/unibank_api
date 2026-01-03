package com.unibank.api.Accounts;

import com.unibank.api.Customers.CustomersTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AccountsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customers_id")
    private CustomersTable customersTable;

    private String accountNumber;
    private String accountType;
    private double balance;
    private String status;
    private LocalDateTime created_at;
}
