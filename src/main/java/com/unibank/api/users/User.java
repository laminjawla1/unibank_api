package com.unibank.api.users;

import com.unibank.api.commons.BaseEntity;
import com.unibank.api.roles.Role;
import com.unibank.api.users.dtos.UserResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    private String firstName;
    private String lastName;
    @Column(unique = true) private String email;
    @Column(unique = true) private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_to_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public UserResponseDTO toUserResponseDTO() {
        UserResponseDTO response = new UserResponseDTO();
        response.setUuid(getUuid());
        response.setFirstName(firstName);
        response.setLastName(lastName);
        response.setEmail(email);
        response.setCreatedAt(getCreatedAt());
        response.setUpdatedAt(getUpdatedAt());
        response.setUsername(username);
        response.setRoles(getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        response.setStatus(getStatus());
        return response;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }
}
