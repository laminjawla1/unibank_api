package com.unibank.api.Transactions;

import com.unibank.api.Transactions.DTO.DepositDTO.DepoRequest;
import com.unibank.api.Transactions.DTO.DepositDTO.DepoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionsApi {

    private final TransactionsService transactionsService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepoRequest depoRequest){
        return transactionsService.deposit(depoRequest);
    }


    @PostMapping("/withdraw")
    public String withdraw(){
        return null;
    }
}
