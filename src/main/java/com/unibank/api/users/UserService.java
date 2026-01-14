package com.unibank.api.users;

import com.unibank.api.configurations.jwt.JwtUtil;
import com.unibank.api.exceptions.UserAlreadyExistsException;
import com.unibank.api.exceptions.RoleNotFoundException;
import com.unibank.api.roles.Role;
import com.unibank.api.roles.RoleRepository;
import com.unibank.api.users.dtos.UserCreateDTO;
import com.unibank.api.users.dtos.UserLoginDTO;
import com.unibank.api.users.dtos.UserLoginResponseDTO;
import com.unibank.api.users.dtos.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    public void registerUser(UserCreateDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username is already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email is already registered");
        }

        User user = buildUser(request);
        userRepository.save(user);
    }

    private User buildUser(UserCreateDTO request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<UUID> requestedRoleIds = new HashSet<>(request.getRoleIds());
        List<Role> roles = new ArrayList<>();
        roleRepository.findAllById(requestedRoleIds).forEach(roles::add);

        if (roles.size() != requestedRoleIds.size()) {
            throw new RoleNotFoundException("One or more roleIds are invalid");
        }

        user.setRoles(new HashSet<>(roles));
        return user;
    }

    public UserLoginResponseDTO login(UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        UserLoginResponseDTO loginResponse = new UserLoginResponseDTO();
        loginResponse.setToken(jwt);
        loginResponse.setUser(Objects.requireNonNull(user).toUserResponseDTO());

        return loginResponse;
    }

    public List<UserResponseDTO> getUsers() {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : userRepository.findAll())
            userResponseDTOS.add(user.toUserResponseDTO());
        return userResponseDTOS;
    }

    public UserResponseDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new org.springframework.security.authentication.InsufficientAuthenticationException("Not authenticated");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User user)) {
            throw new org.springframework.security.authentication.InsufficientAuthenticationException("Invalid authentication principal");
        }

        return user.toUserResponseDTO();
    }
}
