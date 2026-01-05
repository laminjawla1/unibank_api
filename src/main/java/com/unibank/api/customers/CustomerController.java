package com.unibank.api.customers;

import com.unibank.api.customers.dto.CustomerCreateDTO;
import com.unibank.api.customers.dto.CustomerResponseDTO;
import com.unibank.api.customers.dto.CustomerUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /* LIST ALL */
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    /* GET ONE */
    @GetMapping("/{uuid}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable UUID uuid) {
        return ResponseEntity.ok(customerService.getCustomer(uuid));
    }

    /* CREATE */
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(
            @RequestBody CustomerCreateDTO request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    /* UPDATE */
    @PutMapping("/{uuid}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable UUID uuid,
            @RequestBody CustomerUpdateDTO request) {
        return ResponseEntity.ok(customerService.updateCustomer(uuid, request));
    }

    /* DELETE (SOFT DELETE) */
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID uuid) {
        customerService.deleteCustomer(uuid);
        return ResponseEntity.noContent().build();
    }
}
