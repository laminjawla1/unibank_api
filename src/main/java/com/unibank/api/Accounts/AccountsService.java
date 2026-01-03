package com.unibank.api.Accounts;

import com.unibank.api.Accounts.AccountsDTO.CreateRequest;
import com.unibank.api.Accounts.AccountsDTO.CreateResponse;
import com.unibank.api.Customers.CustomersTable;
import com.unibank.api.Customers.CustomersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

import static java.lang.String.valueOf;

@Service
public class AccountsService {

    private final AccountRepository accountRepository;
    private final CustomersRepository customersRepository;

    public AccountsService(AccountRepository accountRepository, CustomersRepository customersRepository){
        this.accountRepository = accountRepository;
        this.customersRepository = customersRepository;
    }

    public ResponseEntity<?> newAccount(CreateRequest createRequest) {
        if (valueOf(createRequest.getCustomerId()).isBlank()){
            throw new RuntimeException("the customer id is required");
        }

        if (createRequest.getAccountType().isBlank()){
            throw new RuntimeException("the account type is required");
        }
        if (accountRepository.existsById(createRequest.getCustomerId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RuntimeException("the account already exist"));
        }

        if (!customersRepository.existsAllById(createRequest.getCustomerId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RuntimeException("customer does not exist"));
        }

        CustomersTable customersTable = customersRepository.findById(createRequest.getCustomerId()).orElseThrow(()-> new UsernameNotFoundException("the customer not found"));

        Random random = new Random();
        String generated = "00"+random.nextInt(9)+"89"+random.nextInt(100)+"40"+ customersTable.getPhone()+"099"+random.nextInt(50);

        AccountsTable accountsTable = new AccountsTable();
        accountsTable.setAccountType(createRequest.getAccountType());
        accountsTable.setCustomersTable(customersTable);
        accountsTable.setAccountNumber(generated);
        accountsTable.setBalance(00.00);
        accountsTable.setStatus("active");
        accountsTable.setCreated_at(LocalDateTime.now());
        accountRepository.save(accountsTable);

        CreateResponse createResponse = new AccountsMapper().toDTO(accountsTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(createResponse);
    }

    public ResponseEntity<?> getAccountById(long id) {
        AccountsTable accountsTable = accountRepository.findById(id).orElseThrow(()->new RuntimeException("account not found"));
        if (!accountRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RuntimeException("account not found"));
        }
        CreateResponse response = new AccountsMapper().toDTO(accountsTable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<?> allaccountByCustomerId(long id) {
        CustomersTable table = customersRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
        return ResponseEntity.status(HttpStatus.OK).body(table);
    }
}
