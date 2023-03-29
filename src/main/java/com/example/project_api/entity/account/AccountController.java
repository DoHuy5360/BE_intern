package com.example.project_api.entity.account;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired private AccountService accountService;

    @GetMapping("/accountAll")
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> accounts = accountService.getAllaccount();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable String accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account.orElse(null), HttpStatus.OK);
    }


    @PostMapping("/createAcc")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.createAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
    }

    @PutMapping("/{accountId}/updateAcc")
    public ResponseEntity<Account> updateAccount(@PathVariable String accountId, @RequestBody Account account) {
        Account updateAccountExist = accountService.updateAccount(accountId, account);
        return new ResponseEntity<>(updateAccountExist, HttpStatus.OK);
    }

    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<Account> resetPassword(@PathVariable String email, @RequestBody Account account) {
        boolean updatePasswordSuccess = accountService.resetPassword(email, account);
        if(updatePasswordSuccess) {
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{accountId}/deleteAcc")
    public ResponseEntity<Account> deleteAccount(@PathVariable String accountId, Account account) {
        Account deleteAccountExsit = accountService.deleteAccount(accountId, account);
        return new ResponseEntity<>(deleteAccountExsit, HttpStatus.OK);
    }
}
