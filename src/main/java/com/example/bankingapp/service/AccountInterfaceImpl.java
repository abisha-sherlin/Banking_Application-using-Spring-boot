package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.mapper.AccountMapper;
import com.example.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountInterfaceImpl implements AccountInterface {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountdto(savedAccount);

    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account=accountRepository.findById(id).
                orElseThrow(()->new RuntimeException("Account doesnt exist.."));

        return AccountMapper.mapToAccountdto(account);


    }
    @Override
    public AccountDto depositMoney(Long id, AccountDto accountDto) {
        Account account=accountRepository.findById(id).
                orElseThrow(()->new RuntimeException("Account doesnt exist.."));


      Double Total= account.getBalance() +accountDto.getBalance();
      account.setBalance(Total);
       Account account1=accountRepository.save(account);
       return AccountMapper.mapToAccountdto(account1);
    }

    @Override
    public AccountDto withdrawMoney(Long id, Double amount) {
        Account account = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account doesnt exist.."));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        } else {


            Double total = account.getBalance() - amount;
            account.setBalance(total);
            Account account1 = accountRepository.save(account);

            return AccountMapper.mapToAccountdto(account1);
        }
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> listAccounts = accountRepository.findAll();
        return listAccounts.stream()
                .map(account -> AccountMapper.mapToAccountdto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account doesnt exist.."));

        accountRepository.deleteById(id);

    }


}
