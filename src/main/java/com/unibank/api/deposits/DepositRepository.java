package com.unibank.api.deposits;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, UUID> {
}
