package br.com.bank.persistence.dto;

import br.com.bank.persistence.model.AccountType;

public class AccountDto {


    private Long userId;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    private String accountType;
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}