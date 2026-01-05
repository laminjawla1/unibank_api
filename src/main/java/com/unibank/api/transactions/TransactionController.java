package com.unibank.api.transactions;

import com.unibank.api.transactions.dto.TransactionCreateDTO;
import com.unibank.api.transactions.dto.TransactionResponseDTO;
import com.unibank.api.transactions.dto.TransactionTypeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @GetMapping("/types")
    public ResponseEntity<List<TransactionTypeResponseDTO>> getTransactionTypes() {
        return ResponseEntity.ok(transactionService.getTransactionTypes());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TransactionResponseDTO> getTransaction(@PathVariable UUID uuid) {
        return ResponseEntity.ok(transactionService.getTransaction(uuid));
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(
            @RequestBody TransactionCreateDTO request) {
        return ResponseEntity.ok(transactionService.createTransaction(request));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID uuid) {
        transactionService.deleteTransaction(uuid);
        return ResponseEntity.noContent().build();
    }
}
