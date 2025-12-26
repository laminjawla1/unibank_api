package com.unibank.api.Services;

import com.unibank.api.DTOs.LoginDTO.LogRequest;
import com.unibank.api.DTOs.LoginDTO.LogResponse;
import com.unibank.api.DTOs.RegisterDTO.RegisterRequest;
import com.unibank.api.DataBase.Users;
import com.unibank.api.JWTUtils.JwtToken;
import com.unibank.api.Mapper.UserMapper;
import com.unibank.api.Repositories.UsersRepository;
import com.unibank.api.Securities.Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationService {
    private final UsersRepository usersRepository;
    private final Encoder coder;
    private final JwtToken jwtToken;

    public AuthenticationService(UsersRepository usersRepository, Encoder coder, JwtToken jwtToken){
        this.usersRepository = usersRepository;
        this.coder = coder;
        this.jwtToken = jwtToken;
    }

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        if(registerRequest.getPassword().isEmpty() || registerRequest.getPassword().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeException("Password is required"));
        }
        if(registerRequest.getName().isEmpty() || registerRequest.getName().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeException("Name is required"));
        }
        if(registerRequest.getRole() == null){
            registerRequest.setRole("teller");
        }

        if(usersRepository.existsByUsername(registerRequest.getName())
        ){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RuntimeException("User already exist"));
        }

        Users users = new  UserMapper().toEntity(registerRequest);
        users.setPassword(coder.passwordEncoder().encode(registerRequest.getPassword()));
        usersRepository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body( new UserMapper().toResponseDTO(users));
    }

    public ResponseEntity<?> login(LogRequest login) {
       Users users = usersRepository.findByUsername(login.getName()).orElseThrow(()->new UsernameNotFoundException("user no found"));
       if (login.getName() == null && login.getName().isBlank()){
           throw new RuntimeException("username is required");
       }
       if(login.getPassword() == null && login.getPassword().isBlank()){
           throw new RuntimeException("the password is required");
        }
       if (!login.getName().equals(users.getUsername())){
           throw new RuntimeException("Password or user name is invalid");
       }

       if (!coder.passwordEncoder().matches(login.getPassword(), users.getPassword())){
           throw new RuntimeException("Password or user name is invalid");
       }

       String jwtCreatedToken = jwtToken.generateToken(login.getName());
        LogResponse response = new LogResponse();
        response.setToken(jwtCreatedToken);
        response.setMessage("success");
        response.setStatusCode(HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
