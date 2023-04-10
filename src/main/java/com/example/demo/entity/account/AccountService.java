package com.example.demo.entity.account;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;

import lombok.RequiredArgsConstructor;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Account> getAllAccount() {
        return (List<Account>) accountRepository.findAll();
    }

    public ResponseEntity<Account> getAccountById(String AccountUserId) {
        Optional<Account> one_AC = accountRepository.findById(AccountUserId);
        if (one_AC.isPresent()) {
            return new ResponseEntity<>(one_AC.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Account> getAccountByEmail(String emailAcc) {
        Optional<Account> one_AC = accountRepository.findByEmail(emailAcc);
        if (one_AC.isPresent()) {
            return new ResponseEntity<>(one_AC.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Account> storeAccount(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getAccountPassword());
        account.setAccountPassword(encodedPassword);
        accountRepository.save(account);
        Optional<Account> one_AC = accountRepository.findById(account.getAccountId());
        if (one_AC.isPresent()) {
            return new ResponseEntity<>(one_AC.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Account> updateAccount(String id, Account account) {
        Optional<Account> one_AC = accountRepository.findById(id);
        if (one_AC.isPresent()) {
            Account _Account = one_AC.get();
            _Account.setAccountEmail(account.getAccountEmail());
            _Account.setAccountRole(account.getAccountRole());
            accountRepository.save(_Account);
            Optional<Account> exist_AC = accountRepository.findById(account.getAccountId());
            if (exist_AC.isPresent()) {
                return new ResponseEntity<>(exist_AC.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Response resetPassword(Account account) {
        if (account.getAccountPassword().equals(account.getRetypeAccountPassword())) {
            Optional<Account> one_AC = accountRepository.findByEmail(account.getAccountEmail());
            if (one_AC.isPresent()) {
                if (account.getAccountPassword().equals(account.getRetypeAccountPassword())) {
                    Account _Account = one_AC.get();
                    System.out.println(_Account);
                    _Account.setAccountPassword(account.getAccountPassword());
                    accountRepository.save(_Account);
                    return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS);
                } else {
                    return new Response(HttpStatus.BAD_REQUEST, Message.UPDATE_FAIL);
                }
            } else {
                return new Response(HttpStatus.BAD_REQUEST, Message.NOT_MATCH);
            }
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteAccount(String id) {
        Optional<Account> one_AC = accountRepository.findById(id);
        if (one_AC.isPresent()) {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
