package com.unibank.api.APIs;

import com.unibank.api.DTOs.CustomerDTO.CreateCustomerRequest;
import com.unibank.api.DTOs.CustomerDTO.UpdateACustomerRequest;
import com.unibank.api.Services.CustomersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomersApi {

    private final CustomersService customersService;

    public CustomersApi(CustomersService customersService){
        this.customersService = customersService;
    }

    @GetMapping()
    public ResponseEntity<?> allCustomers(){
        return customersService.allCustomers();
    }

    @PostMapping()
    public ResponseEntity<?> createCustomers(@RequestBody CreateCustomerRequest request){
        return customersService.createCustomers(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> oneCustomerById(@PathVariable long id){
        return customersService.oneCustomerById(id);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomersById(@PathVariable long id, @RequestBody UpdateACustomerRequest status){
        return customersService.updateCustomersById(id, status);
    }
}
