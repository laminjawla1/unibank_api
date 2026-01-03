package com.unibank.api.Users;

import com.unibank.api.Users.LoginDTO.LogRequest;
import com.unibank.api.Users.LoginDTO.LogResponse;
import com.unibank.api.Users.RegisterDTO.RegisterRequest;
import com.unibank.api.Securities.JWTUtils.JwtToken;
import com.unibank.api.Securities.Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final Encoder coder;
    private final JwtToken jwtToken;

    public UsersService(UsersRepository usersRepository, Encoder coder, JwtToken jwtToken){
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

        UsersTable usersTable = new UsersMapper().toEntity(registerRequest);
        usersTable.setPassword(coder.passwordEncoder().encode(registerRequest.getPassword()));
        usersRepository.save(usersTable);
        return ResponseEntity.status(HttpStatus.CREATED).body( new UsersMapper().toResponseDTO(usersTable));
    }

    public ResponseEntity<?> login(LogRequest login) {
       UsersTable usersTable = usersRepository.findByUsername(login.getName()).orElseThrow(()->new UsernameNotFoundException("user no found"));
       if (login.getName() == null && login.getName().isBlank()){
           throw new RuntimeException("username is required");
       }
       if(login.getPassword() == null && login.getPassword().isBlank()){
           throw new RuntimeException("the password is required");
        }
       if (!login.getName().equals(usersTable.getUsername())){
           throw new RuntimeException("Password or user name is invalid");
       }

       if (!coder.passwordEncoder().matches(login.getPassword(), usersTable.getPassword())){
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
