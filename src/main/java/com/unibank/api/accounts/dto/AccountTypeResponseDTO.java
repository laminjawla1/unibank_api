package com.unibank.api.accounts.dto;

import com.unibank.api.accounts.EAccountType;
import com.unibank.api.commons.BaseEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AccountTypeResponseDTO {
    private UUID uuid;
    private EAccountType type;
    @Enumerated(EnumType.STRING)
    private BaseEntity.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
