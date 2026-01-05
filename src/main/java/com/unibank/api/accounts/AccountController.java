package com.unibank.api.accounts;

import com.unibank.api.accounts.dto.AccountCreateDTO;
import com.unibank.api.accounts.dto.AccountResponseDTO;
import com.unibank.api.accounts.dto.AccountTypeResponseDTO;
import com.unibank.api.accounts.dto.AccountUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable UUID uuid) {
        return ResponseEntity.ok(accountService.getAccount(uuid));
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Valid AccountCreateDTO request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<AccountResponseDTO> updateAccount(
            @PathVariable UUID uuid,
            @RequestBody AccountUpdateDTO request) {
        return ResponseEntity.ok(accountService.updateAccount(uuid, request));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID uuid) {
        accountService.deleteAccount(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/types")
    public ResponseEntity<List<AccountTypeResponseDTO>> getAccountTypes() {
        return ResponseEntity.ok(accountService.getAccountTypes());
    }
}
