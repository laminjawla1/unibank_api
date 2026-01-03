package com.unibank.api.Transactions;

import com.unibank.api.Accounts.AccountRepository;
import com.unibank.api.Accounts.AccountsTable;
import com.unibank.api.Customers.CustomersRepository;
import com.unibank.api.Customers.CustomersTable;
import com.unibank.api.Securities.UniBankUserDetailsServices;
import com.unibank.api.Transactions.DTO.DepositDTO.DepoRequest;
import com.unibank.api.Transactions.DTO.DepositDTO.DepoResponse;
import com.unibank.api.Users.UsersRepository;
import com.unibank.api.Users.UsersTable;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;

import static java.lang.String.valueOf;

@Service
@AllArgsConstructor
@Transactional
public class TransactionsService {
    private final TransactionsRepository transactionsRepository;
    private final AccountRepository accountRepository;
    private final UsersRepository usersRepository;
    private final CustomersRepository customersRepository;

    public ResponseEntity<?> deposit(DepoRequest depoRequest) {


        if (valueOf(depoRequest.getAmount()).isBlank()){
            Exception exception = new RuntimeException("amount is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
        }
        if (valueOf(depoRequest.getUsersId()).isBlank()){
            Exception exception = new RuntimeException("user id is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
        }
        if (valueOf(depoRequest.getAccountId()).isBlank()){
            Exception exception = new RuntimeException("account id is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
        }


        AccountsTable findAccount = accountRepository.findById(depoRequest.getAccountId()).orElseThrow(
                ()-> new UsernameNotFoundException("Account not found")
        );
        UsersTable findUser = usersRepository.findById(depoRequest.getUsersId()).orElseThrow(
                ()-> new UsernameNotFoundException("User not found")
        );
        CustomersTable findCustomer = customersRepository.findById(findAccount.getCustomersTable().getId()).orElseThrow(
                ()-> new UsernameNotFoundException("customer not found")
        );

        TransactionsTable transactionsTable = new TransactionsTable();
        transactionsTable.setAmount(depoRequest.getAmount());
        transactionsTable.setUsersTable(findUser);
        transactionsTable.setAccountsTable(findAccount);
        transactionsTable.setCreated_at(new Date(System.currentTimeMillis()));
        transactionsTable.setType(findAccount.getAccountType());

        findAccount.setBalance(depoRequest.getAmount()+findAccount.getBalance());
        findAccount.setCustomersTable(findCustomer);

        transactionsRepository.save(transactionsTable);
        accountRepository.save(findAccount);

        DepoResponse depoResponse = new DepoResponse();
        depoResponse.setDepositedAt(transactionsTable.getCreated_at());
        depoResponse.setDepositedBy(transactionsTable.getUsersTable().getUsername());
        depoResponse.setId(transactionsTable.getId());
        depoResponse.setCustomerName(findCustomer.getFullName());
        depoResponse.setAmount(transactionsTable.getAmount());


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(depoResponse);

    }
}
