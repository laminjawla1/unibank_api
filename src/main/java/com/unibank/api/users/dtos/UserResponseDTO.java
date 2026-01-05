package com.unibank.api.users.dtos;

import com.unibank.api.commons.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponseDTO extends BaseResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Set<String> roles = new HashSet<>();
}
