package com.unibank.api.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final AdminUserSeederService adminUserSeederService;
    private final RoleSeederServiceService roleSeederService;

    public DatabaseSeeder(AdminUserSeederService adminUserSeederService, RoleSeederServiceService roleSeederService) {
        this.adminUserSeederService = adminUserSeederService;
        this.roleSeederService = roleSeederService;
    }

    @Override
    public void run(String... args) {
        roleSeederService.seed();
        adminUserSeederService.seed();
    }
}
