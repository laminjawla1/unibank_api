package com.unibank.api.roles.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unibank.api.commons.BaseEntity;
import com.unibank.api.roles.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDTO {
    private UUID uuid;
    private ERole name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private BaseEntity.Status status;
}
