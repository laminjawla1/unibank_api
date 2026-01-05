package com.unibank.api.accounts;

import com.unibank.api.accounts.dto.AccountResponseDTO;
import com.unibank.api.commons.BaseEntity;
import com.unibank.api.customers.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @Column(unique = true)
    Long accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id")
    AccountType accountType;

    Double balance = 0.0;

    public AccountResponseDTO toResponseDTO() {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setUuid(getUuid());
        dto.setAccountNumber(accountNumber);
        dto.setBalance(balance);
        dto.setCustomer(customer.toResponseDTO());
        dto.setAccountType(accountType.getType());
        dto.setStatus(getStatus());
        dto.setCreatedAt(getCreatedAt());
        dto.setUpdatedAt(getUpdatedAt());
        return dto;
    }
}
