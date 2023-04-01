package com.example.demo.entity.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/account")
// @RequiredArgsConstructor
@CrossOrigin("*")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<Account>> index() {
        // Page
        List<Account> accounts = accountService.getAllAccount();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> show(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @PostMapping("/store")
    public ResponseEntity<Account> store(@RequestBody Account account) {

        return accountService.storeAccount(account);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Account> updateAccount(@PathVariable String id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);

    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> reset(@RequestBody Account account) {
        return accountService.resetPassword(account);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable String id) {
        return accountService.deleteAccount(id);
    }
}
