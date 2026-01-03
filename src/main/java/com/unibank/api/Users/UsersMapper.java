package com.unibank.api.Users;

import com.unibank.api.Users.RegisterDTO.RegisterRequest;
import com.unibank.api.Users.RegisterDTO.RegisterResponse;

import java.sql.Date;

public class UsersMapper {
    public UsersTable toEntity(RegisterRequest registerRequest){
       UsersTable usersTable = new UsersTable();
       usersTable.setUsername(registerRequest.getName());
       usersTable.setPassword(registerRequest.getPassword());
       usersTable.setRole(registerRequest.getRole());
       usersTable.setStatus(registerRequest.getStatus());
       usersTable.setCreate_at(new Date(System.currentTimeMillis()));

       return usersTable;
    }

    public RegisterResponse toResponseDTO(UsersTable usersTable){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(usersTable.getId());
        registerResponse.setName(usersTable.getUsername());
        registerResponse.setRole(usersTable.getRole());
        registerResponse.setCreate_at(usersTable.getCreate_at());

        return registerResponse;
    }
}
