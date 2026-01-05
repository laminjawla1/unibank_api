package com.unibank.api.accounts;

import com.unibank.api.accounts.dto.AccountTypeResponseDTO;
import com.unibank.api.commons.BaseEntity;
import com.unibank.api.roles.ERole;
import com.unibank.api.roles.dto.RoleResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_types")
public class AccountType extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private EAccountType type;

    public AccountTypeResponseDTO toAccountTypeResponseDTO() {
        AccountTypeResponseDTO responseDTO = new AccountTypeResponseDTO();
        responseDTO.setUuid(getUuid());
        responseDTO.setType(getType());
        responseDTO.setStatus(getStatus());
        responseDTO.setCreatedAt(getCreatedAt());
        responseDTO.setUpdatedAt(getUpdatedAt());
        return responseDTO;
    }
}
