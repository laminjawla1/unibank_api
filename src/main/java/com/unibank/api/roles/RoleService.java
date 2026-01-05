package com.unibank.api.roles;

import com.unibank.api.roles.dto.RoleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleResponseDTO> getRoles() {
        List<RoleResponseDTO> responseDTOS = new ArrayList<>();
        for (Role role : roleRepository.findAllByOrderByNameAsc())
            responseDTOS.add(role.toRoleResponseDTO());
        System.out.println(responseDTOS);
        return responseDTOS;
    }
}
