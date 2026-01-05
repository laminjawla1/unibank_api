package com.unibank.api.reports.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponseDTO {
    private Long usersCount;
    private Long customersCount;
    private Long accountsCount;
    private Double transactionsTotal;
    private Long depositsCount;
    private Long withdrawalsCount;
}
