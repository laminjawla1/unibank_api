package com.unibank.api.Customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersTable, Long> {
    boolean existsAllById(Long id);
    CustomersTable findCustomersByEmail(String email);
    boolean existsByEmail(String email);

    CustomersTable findById(CustomersTable customersTable);
}
