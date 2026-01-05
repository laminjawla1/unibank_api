package com.unibank.api.commons;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BaseResponseDTO {
    private UUID uuid;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private BaseEntity.Status status;
}
