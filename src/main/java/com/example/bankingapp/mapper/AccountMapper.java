package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;

public class AccountMapper {

public static Account mapToAccount(AccountDto accountDto){

    return Account.builder()
            .id(accountDto.getId())
            .accountHolderName(accountDto.getAccountHolderName())
            .balance(accountDto.getBalance())
            .build();
}

public static AccountDto mapToAccountdto(Account account){
    return AccountDto
            .builder()
            .id(account.getId())
            .accountHolderName(account.getAccountHolderName())
            .balance(account.getBalance())
            .build();
}



}
