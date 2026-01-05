package com.unibank.api.transactions.dto;

import com.unibank.api.commons.BaseEntity;
import com.unibank.api.transactions.ETransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionTypeResponseDTO {
    private UUID uuid;
    private ETransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private BaseEntity.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
