package com.unibank.api.users;

import com.unibank.api.commons.MessageResponse;
import com.unibank.api.users.dtos.UserCreateDTO;
import com.unibank.api.users.dtos.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok(userService.login(userLoginDTO));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> registerUser(@RequestBody @Valid UserCreateDTO request) {
        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Registration successful"));
    }
}
