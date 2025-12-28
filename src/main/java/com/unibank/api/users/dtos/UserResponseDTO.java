package com.unibank.api.users.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unibank.api.commons.BaseEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Set<String> roles = new HashSet<>();
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private BaseEntity.Status status;
}
