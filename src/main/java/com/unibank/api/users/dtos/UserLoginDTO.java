package com.unibank.api.users.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user login requests.
 * Accepts either username or email as the identifier.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username must not exceed 100 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 1, max = 128, message = "Password length is invalid")
    private String password;

    @Override
    public String toString() {
        return "LoginRequest(username=" + username + ", password=****)";
    }

}