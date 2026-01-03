package com.unibank.api.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsTable, Long> {
    Optional<TransactionsTable> findById(Long id);
}
