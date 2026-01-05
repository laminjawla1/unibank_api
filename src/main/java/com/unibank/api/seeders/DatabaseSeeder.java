package com.unibank.api.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final AdminUserSeederService adminUserSeederService;
    private final RoleSeederServiceService roleSeederService;
    private final AccountTypeSeederServiceService accountTypeSeederServiceService;
    private final TransactionTypeSeederServiceService transactionTypeSeederServiceService;

    public DatabaseSeeder(
            AdminUserSeederService adminUserSeederService,
            RoleSeederServiceService roleSeederService,
            AccountTypeSeederServiceService accountTypeSeederServiceService,
            TransactionTypeSeederServiceService transactionTypeSeederServiceService
    ) {
        this.adminUserSeederService = adminUserSeederService;
        this.roleSeederService = roleSeederService;
        this.accountTypeSeederServiceService = accountTypeSeederServiceService;
        this.transactionTypeSeederServiceService = transactionTypeSeederServiceService;
    }

    @Override
    public void run(String... args) {
        roleSeederService.seed();
        adminUserSeederService.seed();
        accountTypeSeederServiceService.seed();
        transactionTypeSeederServiceService.seed();
    }
}
