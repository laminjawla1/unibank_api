package com.unibank.api.seeders;

import com.unibank.api.roles.ERole;
import com.unibank.api.roles.Role;
import com.unibank.api.roles.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleSeederServiceService implements SeederService {
    private final RoleRepository roleRepository;

    public RoleSeederServiceService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seed() {
        for (ERole eRole : ERole.values()) {
            if (roleRepository.findByName(eRole).isEmpty()) {
                Role role = new Role();
                role.setName(eRole);
                roleRepository.save(role);
            }
        }
    }
}
