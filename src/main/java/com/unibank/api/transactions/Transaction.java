package com.unibank.api.transactions;

import com.unibank.api.accounts.Account;
import com.unibank.api.commons.BaseEntity;
import com.unibank.api.transactions.dto.TransactionResponseDTO;
import com.unibank.api.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performed_by")
    private User performedBy;

    public TransactionResponseDTO toResponseDTO() {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setUuid(getUuid());
        dto.setAccount(account.toResponseDTO());
        dto.setTransactionType(transactionType.getTransactionType());
        dto.setAmount(amount);
        dto.setPerformedBy(performedBy != null ? performedBy.toUserResponseDTO() : null);
        dto.setStatus(getStatus());
        dto.setCreatedAt(getCreatedAt());
        dto.setUpdatedAt(getUpdatedAt());
        return dto;
    }
}
