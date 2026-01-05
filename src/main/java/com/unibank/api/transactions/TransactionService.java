package com.unibank.api.transactions;

import com.unibank.api.accounts.Account;
import com.unibank.api.accounts.AccountRepository;
import com.unibank.api.commons.BaseEntity;
import com.unibank.api.transactions.dto.TransactionCreateDTO;
import com.unibank.api.transactions.dto.TransactionResponseDTO;
import com.unibank.api.transactions.dto.TransactionTypeResponseDTO;
import com.unibank.api.users.User;
import com.unibank.api.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            TransactionTypeRepository transactionTypeRepository,
            AccountRepository accountRepository,
            UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    /* CREATE TRANSACTION (DEPOSIT / WITHDRAWAL) */
    @Transactional
    public TransactionResponseDTO createTransaction(TransactionCreateDTO request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        TransactionType transactionType = transactionTypeRepository
                .findById(request.getTransactionTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Transaction type not found"));

        User user = request.getPerformedBy() != null
                ? userRepository.findById(request.getPerformedBy())
                .orElseThrow(() -> new EntityNotFoundException("User not found"))
                : null;

        validateAndApplyBalance(account, transactionType, request.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(request.getAmount());
        transaction.setPerformedBy(user);

        accountRepository.save(account);

        return transactionRepository.save(transaction).toResponseDTO();
    }

    /* READ ALL */
    public List<TransactionResponseDTO> getTransactions() {
        return transactionRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(Transaction::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* READ ONE */
    public TransactionResponseDTO getTransaction(UUID uuid) {
        Transaction transaction = transactionRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

        return transaction.toResponseDTO();
    }

    /* SOFT DELETE */
    public void deleteTransaction(UUID uuid) {
        Transaction transaction = transactionRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

        transaction.setStatus(BaseEntity.Status.INACTIVE);
        transactionRepository.save(transaction);
    }

    /* BALANCE RULES */
    private void validateAndApplyBalance(Account account, TransactionType type, Double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        if (type.getTransactionType() == ETransactionType.WITHDRAWAL) {
            if (account.getBalance() < amount) {
                throw new IllegalStateException("Insufficient balance");
            }
            account.setBalance(account.getBalance() - amount);
        }

        if (type.getTransactionType() == ETransactionType.DEPOSIT) {
            account.setBalance(account.getBalance() + amount);
        }
    }

    public List<TransactionTypeResponseDTO> getTransactionTypes() {
        return transactionTypeRepository.findAll()
                .stream()
                .map(TransactionType::toTransactionTypeResponseDTO)
                .collect(Collectors.toList());
    }
}
