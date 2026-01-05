package com.unibank.api.seeders;

import com.unibank.api.accounts.AccountType;
import com.unibank.api.accounts.AccountTypeRepository;
import com.unibank.api.accounts.EAccountType;
import com.unibank.api.transactions.ETransactionType;
import com.unibank.api.transactions.TransactionType;
import com.unibank.api.transactions.TransactionTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeSeederServiceService implements SeederService {
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeSeederServiceService(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public void seed() {
        for (ETransactionType eTransactionType : ETransactionType.values()) {
            if (transactionTypeRepository.findByTransactionType(eTransactionType).isEmpty()) {
                TransactionType transactionType = new TransactionType();
                transactionType.setTransactionType(eTransactionType);
                transactionTypeRepository.save(transactionType);
            }
        }
    }
}
