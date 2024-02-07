package com.example.bankingapp.controller;


import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.service.AccountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private AccountInterface accountInterface;

    @PostMapping("/addAccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){

        return new ResponseEntity<>(accountInterface.createAccount(accountDto), HttpStatus.CREATED);

    }

    @GetMapping("/{id}/getAccount")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){

        return new ResponseEntity<>(accountInterface.getAccountById(id),HttpStatus.OK);

    }
    //deposit
    @PutMapping("/{id}/depositAccount")
    public ResponseEntity<AccountDto> depositAccount(@PathVariable Long id,
                                                     @RequestBody AccountDto accountDto){

        return new ResponseEntity<>(accountInterface.depositMoney(id,accountDto),HttpStatus.OK);

    }

    @PutMapping("/{id}/withdrawAccount")
    public ResponseEntity<AccountDto> withdrawAccount(@PathVariable Long id,
                                                      @RequestBody Map<String,Double> amount){

        Double balance=amount.get("amount");
        return new ResponseEntity<>(accountInterface.withdrawMoney(id,balance),HttpStatus.OK);

    }

    @GetMapping("/getAccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return new ResponseEntity<List<AccountDto>>(accountInterface.getAllAccounts(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccounts(Long id){

        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);

    }
}
