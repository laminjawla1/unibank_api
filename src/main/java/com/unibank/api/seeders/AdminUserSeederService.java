package com.unibank.api.seeders;

import com.unibank.api.exceptions.RoleNotFoundException;
import com.unibank.api.roles.ERole;
import com.unibank.api.roles.RoleRepository;
import com.unibank.api.users.User;
import com.unibank.api.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class AdminUserSeederService implements SeederService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private static final String DEFAULT_PASSWORD = "Pass@123";

    public AdminUserSeederService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void seed() {
        seedUser("admin", "Admin", "User", "admin@unibank.com", ERole.ROLE_ADMIN);
        seedUser("teller", "Teller", "User", "teller@unibank.com", ERole.ROLE_TELLER);
        seedUser("finance", "Finance", "User", "finance@unibank.com", ERole.ROLE_FINANCE);
        seedUser("compliance", "Compliance", "User", "compliance@unibank.com", ERole.ROLE_COMPLIANCE);
    }

    private void seedUser(String username, String firstName, String lastName, String email, ERole role) {
        if (userRepository.existsByUsername(username)) return;

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        user.setRoles(Set.of(roleRepository.findByName(role)
                .orElseThrow(() -> new RoleNotFoundException(role + " role not found"))));
        userRepository.save(user);
    }
}
