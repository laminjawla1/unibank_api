package com.unibank.api.Accounts.AccountsDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRequest {
    private long customerId;
    private String accountType;
}
