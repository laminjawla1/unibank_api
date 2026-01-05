package com.unibank.api.roles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
    Optional<Role> findByName(ERole name);

    List<Role> findAllByOrderByNameAsc();
}
