package com.example.project_api.entity.account;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired private AccountResponsesity accountResponsesity;

    public List<Account> getAllaccount() {
        return (List<Account>) accountResponsesity.findAll();
    }

    public Optional<Account> getAccountById(String AccountUserId) {
        return accountResponsesity.findById(AccountUserId);
    }

    public Optional<Account> getAccountByEmail(String emailAcc){
        return accountResponsesity.findByEmail(emailAcc);
    }

    public Account createAccount(Account account) {
        accountResponsesity.save(account);
        return account;
    }

    public Account updateAccount(String account_id, Account account) {
        Account updateAccountexist = getAccountById(account_id).orElse(null);
        if (updateAccountexist != null) {
            updateAccountexist.setEmail(account.getEmail());
            updateAccountexist.setPassword(account.getPassword());
            accountResponsesity.save(updateAccountexist);
        }
        return updateAccountexist;
    }

    public boolean resetPassword(String email, Account updateAccount){
        boolean flag = false;
        Account updatePassexist = accountResponsesity.findByEmail(email).orElse(null);
        if (updatePassexist != null) {
            updatePassexist.setPassword(updateAccount.getPassword());
            updatePassexist.setRetypePassword(updateAccount.getRetypePassword());
            if(updatePassexist.getPassword().equals(updatePassexist.getRetypePassword())){
                accountResponsesity.save(updatePassexist);
                flag = true;
            }else {
                flag = false;
            }
        }
        return flag;
    }

    public Account deleteAccount(String account_id, Account account) {
        accountResponsesity.deleteById(account_id);
        return account;
    }
    
}
