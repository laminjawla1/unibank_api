package com.unibank.api.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersTable, Long> {
    Optional<UsersTable> findByUsername(String username);
    boolean existsByUsername(String username);
}
