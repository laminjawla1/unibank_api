package com.unibank.api.Users;

import com.unibank.api.Users.LoginDTO.LogRequest;
import com.unibank.api.Users.RegisterDTO.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsersApi {

    private final UsersService usersService;

    public UsersApi(UsersService usersService){
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        return usersService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogRequest login){
        return usersService.login(login);
    }

}
