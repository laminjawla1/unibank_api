package com.unibank.api.accounts;

import com.unibank.api.accounts.dto.AccountCreateDTO;
import com.unibank.api.accounts.dto.AccountResponseDTO;
import com.unibank.api.accounts.dto.AccountTypeResponseDTO;
import com.unibank.api.accounts.dto.AccountUpdateDTO;
import com.unibank.api.commons.BaseEntity;
import com.unibank.api.customers.Customer;
import com.unibank.api.customers.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;

    public AccountService(
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            AccountTypeRepository accountTypeRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountTypeRepository = accountTypeRepository;
    }

    /* CREATE */
    public AccountResponseDTO createAccount(AccountCreateDTO request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        AccountType accountType = accountTypeRepository.findById(request.getAccountTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Account type not found"));

        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setBalance(request.getInitialBalance());
        account.setAccountNumber(generateAccountNumber());

        return accountRepository.save(account).toResponseDTO();
    }

    /* READ ALL */
    public List<AccountResponseDTO> getAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(Account::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* READ ONE */
    public AccountResponseDTO getAccount(UUID uuid) {
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        return account.toResponseDTO();
    }

    /* UPDATE */
    public AccountResponseDTO updateAccount(UUID uuid, AccountUpdateDTO request) {

        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        AccountType accountType = accountTypeRepository.findById(request.getAccountTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Account type not found"));

        account.setAccountType(accountType);

        return accountRepository.save(account).toResponseDTO();
    }

    /* DELETE (SOFT DELETE) */
    public void deleteAccount(UUID uuid) {
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        account.setStatus(BaseEntity.Status.INACTIVE);
        accountRepository.save(account);
    }

    /* SIMPLE ACCOUNT NUMBER GENERATOR */
    private Long generateAccountNumber() {
        return ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
    }

    public List<AccountTypeResponseDTO> getAccountTypes() {
        return accountTypeRepository.findAll()
                .stream()
                .map(AccountType::toAccountTypeResponseDTO)
                .collect(Collectors.toList());
    }
}
