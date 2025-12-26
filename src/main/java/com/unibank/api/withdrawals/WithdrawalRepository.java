package com.unibank.api.withdrawals;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WithdrawalRepository extends CrudRepository<Withdrawal, UUID> {
}
