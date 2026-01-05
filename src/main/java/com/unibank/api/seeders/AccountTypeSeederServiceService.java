package com.unibank.api.seeders;

import com.unibank.api.accounts.AccountType;
import com.unibank.api.accounts.AccountTypeRepository;
import com.unibank.api.accounts.EAccountType;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeSeederServiceService implements SeederService {
    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeSeederServiceService(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public void seed() {
        for (EAccountType eType : EAccountType.values()) {
            if (accountTypeRepository.findByType(eType).isEmpty()) {
                AccountType type = new AccountType();
                type.setType(eType);
                accountTypeRepository.save(type);
            }
        }
    }
}
