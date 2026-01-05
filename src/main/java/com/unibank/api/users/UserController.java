package com.unibank.api.users;

import com.unibank.api.commons.MessageResponse;
import com.unibank.api.users.dtos.UserCreateDTO;
import com.unibank.api.users.dtos.UserLoginDTO;
import com.unibank.api.users.dtos.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
