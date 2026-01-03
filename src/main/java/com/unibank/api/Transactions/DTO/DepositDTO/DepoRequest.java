package com.unibank.api.Transactions.DTO.DepositDTO;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class DepoRequest {
    private Long accountId;
    private double amount;
    private Long usersId;
}
