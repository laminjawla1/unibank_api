package com.unibank.api.transactions.dto;

import com.unibank.api.accounts.dto.AccountResponseDTO;
import com.unibank.api.commons.BaseEntity;
import com.unibank.api.transactions.ETransactionType;
import com.unibank.api.users.User;
import com.unibank.api.users.dtos.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionResponseDTO {
    private UUID uuid;
    private AccountResponseDTO account;
    private ETransactionType transactionType;
    private Double amount;
    private UserResponseDTO performedBy;
    private BaseEntity.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
