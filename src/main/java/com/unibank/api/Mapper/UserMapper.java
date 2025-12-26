package com.unibank.api.Mapper;

import com.unibank.api.DTOs.RegisterDTO.RegisterRequest;
import com.unibank.api.DTOs.RegisterDTO.RegisterResponse;
import com.unibank.api.DataBase.Users;

import java.sql.Date;

public class UserMapper {
    public Users toEntity(RegisterRequest registerRequest){
       Users users = new Users();
       users.setUsername(registerRequest.getName());
       users.setPassword(registerRequest.getPassword());
       users.setRole(registerRequest.getRole());
       users.setStatus(registerRequest.getStatus());
       users.setCreate_at(new Date(System.currentTimeMillis()));

       return users;
    }

    public RegisterResponse toResponseDTO(Users users){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(users.getId());
        registerResponse.setName(users.getUsername());
        registerResponse.setRole(users.getRole());
        registerResponse.setCreate_at(users.getCreate_at());

        return registerResponse;
    }
}
