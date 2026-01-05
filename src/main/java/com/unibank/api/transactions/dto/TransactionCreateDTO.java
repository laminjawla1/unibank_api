package com.unibank.api.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionCreateDTO {
    private UUID accountId;
    private UUID transactionTypeId;
    private Double amount;
    private UUID performedBy;
}
