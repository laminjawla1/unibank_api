package com.unibank.api.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountsTable, Long> {
    @Override
    Optional<AccountsTable> findById(Long id);

    @Override
    boolean existsById(Long aLong);
    AccountsTable findByAccountNumber(String accountNumber);
}
