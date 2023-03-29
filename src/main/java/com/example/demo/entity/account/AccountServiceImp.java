package com.example.demo.entity.account;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.utilities.Time;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts(){
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }


    @Override
    public Account getAccountById(String id) {
        Optional<Account> one_account = accountRepository.findById(id);
        return one_account.orElse(null);
    }

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    
    @Override
    public ResponseEntity<Account> updateAccount(String id, Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
    
        if (!optionalAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Account existingAccount = optionalAccount.get();
    
        existingAccount.setAccountEmail(account.getAccountEmail());
        existingAccount.setAccountPassword(account.getAccountPassword());
        existingAccount.setAccountRole(account.getAccountRole());
        existingAccount.setUpdateAt(Time.getCurrentDate());
    
        Account updatedAccount = accountRepository.save(existingAccount);
    
        return ResponseEntity.ok(updatedAccount);
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
}
