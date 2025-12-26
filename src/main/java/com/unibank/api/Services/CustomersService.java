package com.unibank.api.Services;

import com.unibank.api.DTOs.CustomerDTO.CreateCustomerRequest;
import com.unibank.api.DTOs.CustomerDTO.CreateCustomerResponse;
import com.unibank.api.DTOs.CustomerDTO.UpdateACustomerRequest;
import com.unibank.api.DTOs.CustomerDTO.UpdateACustomerResponse;
import com.unibank.api.DataBase.Customers;
import com.unibank.api.Mapper.CustomerMapper;
import com.unibank.api.Repositories.CustomersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    // constructor to inject the customersRepository
    public CustomersService(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }

    //the service route to create a new customer
    public ResponseEntity<?> createCustomers(CreateCustomerRequest request) {
        Customers customers = new CustomerMapper().toEntity(request);
        customersRepository.save(customers);

        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setMessage("New customer create");
        response.setName(customers.getFullName());
        response.setId(customers.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //A route to get all customers
    public ResponseEntity<?> allCustomers() {
        List<Customers> customers = customersRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    //A route to get customer by customer id
    public ResponseEntity<?> oneCustomerById(long id) {
      if (customersRepository.existsAllById(id)){
          Optional<Customers> customers = customersRepository.findById(id);
          return ResponseEntity.status(HttpStatus.OK).body(customers);
      }
      return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer id: "+id+" is found");
    }

    // A route to update a customer status
    public ResponseEntity<?> updateCustomersById(long id, UpdateACustomerRequest status) {
        if (!customersRepository.existsAllById(id)){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer id: "+id+" is found");
        }
        Customers customers = customersRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("user not found"));
        customers.setStatus(status.getStatus());
        customersRepository.save(customers);

        UpdateACustomerResponse response = new UpdateACustomerResponse();
        response.setId(customers.getId());
        response.setName(customers.getFullName());
        response.setStatus(customers.getStatus());
        response.setMessage("customer updated.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
