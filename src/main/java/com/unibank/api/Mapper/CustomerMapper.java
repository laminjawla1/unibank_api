package com.unibank.api.Mapper;

import com.unibank.api.DTOs.CustomerDTO.CreateCustomerRequest;
import com.unibank.api.DTOs.CustomerDTO.CreateCustomerResponse;
import com.unibank.api.DataBase.Customers;

import java.sql.Timestamp;

public class CustomerMapper {
    public Customers toEntity(CreateCustomerRequest request){
        Customers customers = new Customers();
        customers.setAddress(request.getAddress());
        customers.setCreated_at(new Timestamp(System.currentTimeMillis()));
        customers.setEmail(request.getEmail());
        customers.setPhone(request.getPhone());
        customers.setFullName(request.getFullName());
        customers.setDateOfBirth(request.getDateOfBirth());
        customers.setNational_id(request.getNational_id());

        return customers;
    }

    public CreateCustomerResponse customerResponse(Customers customers){
        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setId(customers.getId());
        response.setName(customers.getFullName());

        return response;
    }
}
