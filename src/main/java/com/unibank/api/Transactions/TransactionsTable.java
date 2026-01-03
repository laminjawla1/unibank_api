package com.unibank.api.Transactions;

import com.unibank.api.Accounts.AccountsTable;
import com.unibank.api.Users.UsersTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
public class TransactionsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "accounts_id")
    private AccountsTable accountsTable;
    private String type;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UsersTable usersTable;
    private Date created_at;
}
