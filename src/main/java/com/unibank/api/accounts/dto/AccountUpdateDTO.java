package com.unibank.api.accounts.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountUpdateDTO {
    private UUID accountTypeId;
}
