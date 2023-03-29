package com.example.demo.entity.account;

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
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> accounts = accountService.getAllaccount();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        Optional<Account> account = accountService.getAccountById(id);
        return new ResponseEntity<>(account.orElse(null), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.createAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Account> updateAccount(@PathVariable String id, @RequestBody Account account) {
        Account updateAccountExist = accountService.updateAccount(id, account);
        return new ResponseEntity<>(updateAccountExist, HttpStatus.OK);
    }

    @PutMapping("/reset-password/{email}")
    public ResponseEntity<Account> resetPassword(@PathVariable String email, @RequestBody Account account) {
        boolean updatePasswordSuccess = accountService.resetPassword(email, account);
        if (updatePasswordSuccess) {
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Account> deleteAccount(@PathVariable String id, Account account) {
        Account deleteAccountExsit = accountService.deleteAccount(id, account);
        return new ResponseEntity<>(deleteAccountExsit, HttpStatus.OK);
    }
}
