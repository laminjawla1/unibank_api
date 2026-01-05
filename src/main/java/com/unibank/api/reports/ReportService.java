package com.unibank.api.reports;

import com.unibank.api.accounts.AccountRepository;
import com.unibank.api.customers.CustomerRepository;
import com.unibank.api.reports.dto.DashboardResponseDTO;
import com.unibank.api.transactions.*;
import com.unibank.api.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    public ReportService(
            CustomerRepository customerRepository,
            UserRepository userRepository,
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            TransactionTypeRepository transactionTypeRepository
    ) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    public DashboardResponseDTO dashboardReport() {
        TransactionType deposit = transactionTypeRepository.findByTransactionType(ETransactionType.DEPOSIT)
                .orElseThrow(() -> new EntityNotFoundException("Deposit not found"));
        TransactionType withdrawal = transactionTypeRepository.findByTransactionType(ETransactionType.WITHDRAWAL)
                .orElseThrow(() -> new EntityNotFoundException("Withdrawal not found"));
        Double transactionTotal = transactionRepository.findAll().stream().map(Transaction::getAmount)
                .reduce(0.0, Double::sum);

        return DashboardResponseDTO.builder()
                .customersCount(customerRepository.count())
                .usersCount(userRepository.count())
                .accountsCount(accountRepository.count())
                .transactionsTotal(transactionTotal)
                .depositsCount((long) transactionRepository.findAllByTransactionType(deposit).size())
                .withdrawalsCount((long) transactionRepository.findAllByTransactionType(withdrawal).size())
                .build();
    }
}
