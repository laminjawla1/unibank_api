package com.unibank.api.customers.dto;

import com.unibank.api.commons.BaseResponseDTO;
import lombok.*;

import java.util.Date;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerResponseDTO extends BaseResponseDTO {
    private String fullName;
    private Date dob;
    private String phoneNumber;
    private String email;
    private String address;
    private String nationalId;
}
