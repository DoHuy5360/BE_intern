package com.example.demo.entity.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllaccount() {
        return (List<Account>) accountRepository.findAll();
    }

    public Optional<Account> getAccountById(String AccountUserId) {
        return accountRepository.findById(AccountUserId);
    }

    public Optional<Account> getAccountByEmail(String emailAcc) {
        return accountRepository.findByEmail(emailAcc);
    }

    public Account createAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    public Account updateAccount(String account_id, Account account) {
        Account updateAccountexist = getAccountById(account_id).orElse(null);
        if (updateAccountexist != null) {
            updateAccountexist.setAccountEmail(account.getAccountEmail());
            updateAccountexist.setAccountPassword(account.getAccountPassword());
            accountRepository.save(updateAccountexist);
        }
        return updateAccountexist;
    }

    public boolean resetPassword(String email, Account updateAccount) {
        boolean flag = false;
        Account updatePassexist = accountRepository.findByEmail(email).orElse(null);
        if (updatePassexist != null) {
            updatePassexist.setAccountPassword(updateAccount.getAccountPassword());
            updatePassexist.setRetypeAccountPassword(updateAccount.getRetypeAccountPassword());
            if (updatePassexist.getAccountPassword().equals(updatePassexist.getRetypeAccountPassword())) {
                accountRepository.save(updatePassexist);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public Account deleteAccount(String account_id, Account account) {
        accountRepository.deleteById(account_id);
        return account;
    }

}
