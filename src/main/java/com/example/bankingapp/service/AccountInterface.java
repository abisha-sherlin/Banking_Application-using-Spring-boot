package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountDto;

import java.util.List;

public interface AccountInterface {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto depositMoney(Long id,AccountDto accountDto);

    AccountDto withdrawMoney(Long id,Double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
}
