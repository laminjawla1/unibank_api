package com.unibank.api.Accounts;

import com.unibank.api.Accounts.AccountsDTO.CreateResponse;

public class AccountsMapper {
    public CreateResponse toDTO(AccountsTable accountsTable){
        CreateResponse createResponse = new CreateResponse();
        createResponse.setAccountNumber(accountsTable.getAccountNumber());
        createResponse.setCreated_at(accountsTable.getCreated_at());
        createResponse.setBalance(accountsTable.getBalance());
        createResponse.setStatus(accountsTable.getStatus());
        createResponse.setAccountType(accountsTable.getAccountType());

        return createResponse;
    }
}
