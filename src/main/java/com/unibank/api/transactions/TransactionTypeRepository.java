package com.unibank.api.transactions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, UUID> {
    Optional<TransactionType> findByTransactionType(ETransactionType eTransactionType);
}
