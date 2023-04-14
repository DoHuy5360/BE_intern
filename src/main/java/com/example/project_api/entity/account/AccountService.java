package com.example.project_api.entity.account;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project_api.response.Message;
import com.example.project_api.response.Response;

@Service
public class AccountService {
    @Autowired private AccountRepository accountRepository;

    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public List<Account> getAllaccount() {
        return (List<Account>) accountRepository.findAll();
    }

    public Optional<Account> getAccountById(String AccountUserId) {
        return accountRepository.findById(AccountUserId);
    }

    public Optional<Account> getAccountByEmail(String emailAcc){
        return accountRepository.findByEmail(emailAcc);
    }

    public Account createAccount(Account account) {
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

    public Account updateAccount(String account_id, Account account) {
        Account updateAccountexist = getAccountById(account_id).orElse(null);
        if (updateAccountexist != null) {
            updateAccountexist.setEmail(account.getEmail());
            accountRepository.save(updateAccountexist);
        }
        return updateAccountexist;
    }

    public Response resetPassword(String email, Account updateAccount){
        Account updatePassexist = accountRepository.findByEmail(email).orElse(null);
        if (updatePassexist != null) {
            updatePassexist.setPassword(this.passwordEncoder.encode(updateAccount.getPassword()));
            updatePassexist.setRetypePassword(this.passwordEncoder.encode(updateAccount.getRetypePassword()));
            if(updateAccount.getPassword().equals(updateAccount.getRetypePassword())) {
                accountRepository.save(updatePassexist);
                return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS);
            }
        }
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAILED);
    }

    public Account deleteAccount(String account_id, Account account) {
        accountRepository.deleteById(account_id);
        return account;
    }

    public Optional<Account> getAccountResetToken(String resetToken) {
        return accountRepository.findbyResetToken(resetToken);
    }
    
}
