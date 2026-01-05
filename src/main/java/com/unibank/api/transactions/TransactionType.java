package com.unibank.api.transactions;

import com.unibank.api.commons.BaseEntity;
import com.unibank.api.transactions.dto.TransactionTypeResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_types")
public class TransactionType extends BaseEntity {
    @Column(nullable = false, unique = true, length = 50)
    @Enumerated(EnumType.STRING)
    private ETransactionType transactionType;

    public TransactionTypeResponseDTO toTransactionTypeResponseDTO() {
        TransactionTypeResponseDTO responseDTO = new TransactionTypeResponseDTO();
        responseDTO.setUuid(getUuid());
        responseDTO.setTransactionType(getTransactionType());
        responseDTO.setStatus(getStatus());
        responseDTO.setCreatedAt(getCreatedAt());
        responseDTO.setUpdatedAt(getUpdatedAt());
        return responseDTO;
    }
}
