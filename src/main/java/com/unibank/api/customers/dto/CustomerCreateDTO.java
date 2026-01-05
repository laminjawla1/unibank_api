package com.unibank.api.customers.dto;

import lombok.*;

import java.util.Date;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDTO {
    private String fullName;
    private Date dob;
    private String phoneNumber;
    private String email;
    private String address;
    private String nationalId;
}
