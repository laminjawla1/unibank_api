package com.unibank.api.Transactions.DTO.DepositDTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class DepoResponse {
    private Long id;
    private String CustomerName;
    private double amount;
    private String depositedBy;
    private Date depositedAt;
}
