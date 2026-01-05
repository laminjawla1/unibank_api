package com.unibank.api.customers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerUpdateDTO {
    private String fullName;
    private Date dob;
    private String phoneNumber;
    private String email;
    private String address;
    private String nationalId;
}
