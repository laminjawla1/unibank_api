package com.unibank.api.customers;

import com.unibank.api.commons.BaseEntity;
import com.unibank.api.customers.dto.CustomerResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer extends BaseEntity {
    private String fullName;
    private Date dob;
    private String phoneNumber;
    private String email;
    private String address;
    private String nationalId;

    public CustomerResponseDTO toResponseDTO() {
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        responseDTO.setUuid(getUuid());
        responseDTO.setFullName(getFullName());
        responseDTO.setDob(getDob());
        responseDTO.setPhoneNumber(getPhoneNumber());
        responseDTO.setEmail(getEmail());
        responseDTO.setAddress(getAddress());
        responseDTO.setNationalId(getNationalId());
        responseDTO.setCreatedAt(getCreatedAt());
        responseDTO.setUpdatedAt(getUpdatedAt());
        responseDTO.setStatus(getStatus());
        return responseDTO;
    }
}
