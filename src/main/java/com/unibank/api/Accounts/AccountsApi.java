package com.unibank.api.Accounts;

import com.unibank.api.Accounts.AccountsDTO.CreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountsApi {

    private final AccountsService accountsService;

    public AccountsApi(AccountsService accountsService){
        this.accountsService = accountsService;
    }

    @PostMapping()
    public ResponseEntity<?> newAccount(@RequestBody CreateRequest createRequest){
        return accountsService.newAccount(createRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable long id){
        return accountsService.getAccountById(id);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getAccountByCustomerId(@PathVariable long id){
        return  accountsService.allaccountByCustomerId(id);
    }
}
