package com.unibank.api.seeders;

import com.unibank.api.exceptions.RoleNotFoundException;
import com.unibank.api.roles.ERole;
import com.unibank.api.roles.RoleRepository;
import com.unibank.api.users.User;
import com.unibank.api.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminUserSeederService implements SeederService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AdminUserSeederService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void seed() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setUsername("admin");
            admin.setEmail("admin@unibank.com");
            admin.setPassword(passwordEncoder.encode("Pass@123"));
            admin.setRoles(Set.of(roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RoleNotFoundException("Admin role not found"))));
            userRepository.save(admin);
        }
    }
}
