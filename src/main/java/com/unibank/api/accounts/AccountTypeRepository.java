package com.unibank.api.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountTypeRepository extends JpaRepository<AccountType, UUID> {
    Optional<AccountType> findByType(EAccountType type);
}
