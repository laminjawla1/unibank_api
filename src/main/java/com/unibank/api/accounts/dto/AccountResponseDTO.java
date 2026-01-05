package com.unibank.api.accounts.dto;

import com.unibank.api.accounts.EAccountType;
import com.unibank.api.commons.BaseEntity;
import com.unibank.api.customers.dto.CustomerResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AccountResponseDTO {
    private UUID uuid;
    private Long accountNumber;
    private Double balance;

    private CustomerResponseDTO customer;
    private EAccountType accountType;

    private BaseEntity.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
