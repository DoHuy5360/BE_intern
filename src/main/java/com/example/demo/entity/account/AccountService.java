package com.example.demo.entity.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.KIT.Query.EmployeeAccountQuery;
import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.EmployeeAccountTray;
import com.example.demo.entity.employee.Employee;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeAccountQuery employeeAccountQuery;

    public List<EmployeeAccountTray> checkLogin(String email, String password) {
        return employeeAccountQuery.getAccountByEmailPassword(email, password);
    }

    public Response getAllAccount() {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, accounts.size(), accounts);
    }

    public Response getAccountById(String AccountUserId) {
        Optional<Account> one_AC = accountRepository.findById(AccountUserId);
        return (one_AC.isPresent()) ? new Response(HttpStatus.OK, Message.READ_SUCCESS, one_AC.get())
                : new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);

    }

    public Response getAccountByEmail(String emailAcc) {
        Optional<Account> one_AC = accountRepository.findByEmail(emailAcc);
        return (one_AC.isPresent()) ? new Response(HttpStatus.OK, Message.READ_SUCCESS, one_AC.get())
                : new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
    }

    public Response storeAccount(Account account) {
        accountRepository.save(account);
        Optional<Account> one_AC = accountRepository.findById(account.getAccountId());
        return (one_AC.isPresent()) ? new Response(HttpStatus.OK, Message.CREATE_SUCCESS, one_AC.get())
                : new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL);

    }

    @Transactional
    public Response updateAccount(String id, Account account) {
        Optional<Account> one_AC = accountRepository.findById(id);
        if (one_AC.isPresent()) {
            try {
                Account _Account = one_AC.get();
                _Account.setAccountEmail(account.getAccountEmail());
                _Account.setAccountRole(account.getAccountRole());
                accountRepository.save(_Account);
            } catch (Exception e) {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS, one_AC.get());

        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
        }
    }

    @Transactional
    public Response resetPassword(Account account) {
        if (account.getAccountPassword().equals(account.getRetypeAccountPassword())) {
            Optional<Account> one_AC = accountRepository.findByEmail(account.getAccountEmail());
            if (one_AC.isPresent()) {
                try {
                    Account _Account = one_AC.get();
                    _Account.setAccountPassword(account.getAccountPassword());
                    accountRepository.save(_Account);
                } catch (Exception e) {
                    return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
                }
                return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS);
            } else {
                return new Response(HttpStatus.BAD_REQUEST, Message.NOT_FOUND);
            }
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_MATCH);
        }
    }

    public Response deleteAccount(String id) {
        Optional<Account> one_AC = accountRepository.findById(id);
        if (one_AC.isPresent()) {
            try {
                accountRepository.deleteById(id);

            } catch (Exception e) {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.DELETE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.DELETE_SUCCESS);
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
        }
    }

}
