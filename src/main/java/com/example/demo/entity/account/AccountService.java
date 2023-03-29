package com.example.demo.entity.account;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface AccountService {
    List<Account> getAllAccounts();

    Account getAccountById(String id);

    void createAccount(Account account);

    ResponseEntity<Account> updateAccount(String id, Account account);

    void deleteAccount(String id);
}
