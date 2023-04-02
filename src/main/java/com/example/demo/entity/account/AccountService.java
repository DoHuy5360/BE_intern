package com.example.demo.entity.account;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.dto.AccountEmployeeDTO;

public interface AccountService {
    List<Account> getAllAccounts();
    List<AccountEmployeeDTO> getAllAccountsAndEmployees();

    Account getAccountById(String id);

    void createAccount(Account account);

    ResponseEntity<Account> updateAccount(String id, Account account);

    void deleteAccount(String id);

    Account createAccountWithEmployee(AccountRequest request);
    Account updateAccountWithEmployee(String id, AccountRequest request);
    void deleteAccountWithEmployee(String id);
    

}
