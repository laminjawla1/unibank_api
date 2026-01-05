package com.unibank.api.customers;

import com.unibank.api.commons.BaseEntity;
import com.unibank.api.customers.dto.CustomerCreateDTO;
import com.unibank.api.customers.dto.CustomerResponseDTO;
import com.unibank.api.customers.dto.CustomerUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /* CREATE */
    public CustomerResponseDTO createCustomer(CustomerCreateDTO request) {
        Customer customer = new Customer();
        customer.setFullName(request.getFullName());
        customer.setDob(request.getDob());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setNationalId(request.getNationalId());

        return customerRepository.save(customer).toResponseDTO();
    }

    /* READ ALL */
    public List<CustomerResponseDTO> getCustomers() {
        return ((List<Customer>) customerRepository.findAll())
                .stream()
                .map(Customer::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* READ ONE */
    public CustomerResponseDTO getCustomer(UUID uuid) {
        Customer customer = customerRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        return customer.toResponseDTO();
    }

    /* UPDATE */
    public CustomerResponseDTO updateCustomer(UUID uuid, CustomerUpdateDTO request) {
        Customer customer = customerRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setFullName(request.getFullName());
        customer.setDob(request.getDob());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setNationalId(request.getNationalId());

        return customerRepository.save(customer).toResponseDTO();
    }

    /* DELETE (SOFT DELETE) */
    public void deleteCustomer(UUID uuid) {
        Customer customer = customerRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setStatus(BaseEntity.Status.INACTIVE);
        customerRepository.save(customer);
    }
}
