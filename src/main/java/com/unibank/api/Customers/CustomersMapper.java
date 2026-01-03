package com.unibank.api.Customers;

import com.unibank.api.Customers.CustomersDTO.CreateCustomerRequest;
import com.unibank.api.Customers.CustomersDTO.CreateCustomerResponse;

import java.sql.Timestamp;

public class CustomersMapper {
    public CustomersTable toEntity(CreateCustomerRequest request){
        CustomersTable customersTable = new CustomersTable();
        customersTable.setAddress(request.getAddress());
        customersTable.setCreated_at(new Timestamp(System.currentTimeMillis()));
        customersTable.setEmail(request.getEmail());
        customersTable.setPhone(request.getPhone());
        customersTable.setFullName(request.getFullName());
        customersTable.setDateOfBirth(request.getDateOfBirth());
        customersTable.setNational_id(request.getNational_id());
        customersTable.setStatus("active");

        return customersTable;
    }

    public CreateCustomerResponse customerResponse(CustomersTable customersTable){
        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setId(customersTable.getId());
        response.setName(customersTable.getFullName());

        return response;
    }
}
