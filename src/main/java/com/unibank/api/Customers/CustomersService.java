package com.unibank.api.Customers;

import com.unibank.api.Customers.CustomersDTO.CreateCustomerRequest;
import com.unibank.api.Customers.CustomersDTO.CreateCustomerResponse;
import com.unibank.api.Customers.CustomersDTO.UpdateACustomerRequest;
import com.unibank.api.Customers.CustomersDTO.UpdateACustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    // constructor to inject the customersRepository
    public CustomersService(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }

    //the service route to create a new customer
    public ResponseEntity<?> createCustomers(CreateCustomerRequest request) {
        if(request.getFullName() == null || request.getFullName().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeException("name is required"));
        }

        if(request.getDateOfBirth() == null || valueOf(request.getDateOfBirth()).isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeException("date of birth is required"));
        }

        if(request.getPhone() == null || valueOf(request.getPhone()).isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeException("phone is required"));
        }

        if(request.getNational_id() == null || request.getNational_id().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeException("national id is required"));
        }


        if(request.getEmail() == null || request.getEmail().isBlank() || !request.getEmail().contains("@")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeException("email is wrong or empty"));
        }

        if(request.getAddress() == null || request.getAddress().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeException("address id is required"));
        }

        if (customersRepository.existsByEmail(request.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RuntimeException("the customer already exist"));
        }
        CustomersTable customersTable = new CustomersMapper().toEntity(request);
        customersRepository.save(customersTable);

        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setMessage("New customer create");
        response.setName(customersTable.getFullName());
        response.setId(customersTable.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //A route to get all customers
    public ResponseEntity<?> allCustomers() {
        List<CustomersTable> customers = customersRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    //A route to get customer by customer id
    public ResponseEntity<?> oneCustomerById(long id) {
      if (customersRepository.existsAllById(id)){
          Optional<CustomersTable> customers = customersRepository.findById(id);
          return ResponseEntity.status(HttpStatus.OK).body(customers);
      }
      return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer id: "+id+" is found");
    }

    // A route to update a customer status
    public ResponseEntity<?> updateCustomersById(long id, UpdateACustomerRequest status) {
        if (!customersRepository.existsAllById(id)){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer id: "+id+" is found");
        }
        CustomersTable customersTable = customersRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("user not found"));
        customersTable.setStatus(status.getStatus());
        customersRepository.save(customersTable);

        UpdateACustomerResponse response = new UpdateACustomerResponse();
        response.setId(customersTable.getId());
        response.setName(customersTable.getFullName());
        response.setStatus(customersTable.getStatus());
        response.setMessage("customer updated.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
