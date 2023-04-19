package com.example.demo.kit.validation;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.account.Account;
import com.example.demo.entity.account.AccountRepository;
import com.example.demo.kit.res.Message;

public class AccountValidation extends PrimitiveValidation {
    public AccountRepository accountRepository;
    public Account account;
    public String email;
    public String password;
    public String retypePassword;
    public String role;
    public Account entity;

    public AccountValidation(Account account, AccountRepository accountRepository) {
        this.email = account.getAccountEmail();
        this.password = account.getAccountPassword();
        this.role = account.getAccountRole();
        this.retypePassword = account.getRetypeAccountPassword();
        this.accountRepository = accountRepository;
    }

    public AccountValidation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountValidation trackEmailFormat() {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(this.email);
        if (!matcher.matches()) {
            this.errors.add(Message.setInvalid("Email"));
        }
        return this;
    }

    public AccountValidation trackEmailExist() {
        Optional<Account> oneA = accountRepository.findByEmail(this.email);
        if (!oneA.isPresent()) {
            this.errors.add(Message.setExisted("Email"));
        }
        return this;
    }

    public AccountValidation trackEmailNotExist() {
        Optional<Account> oneA = accountRepository.findByEmail(this.email);
        if (oneA.isPresent()) {
            this.errors.add(Message.setExisted("Email"));
        }
        return this;
    }

    public AccountValidation trackPasswordFormat() {
        if (this.password.isBlank()) {
            this.errors.add(Message.setEmptyMessage("Password"));
        } else if (!this.password.matches("^.{1,20}$")) {
            this.errors.add(Message.setLengthLimit("Password length", 20));
        }
        return this;
    }

    public AccountValidation trackPasswordRetype() {
        if (!this.password.equals(this.retypePassword)) {
            this.errors.add(Message.setInvalid("Password"));
        }
        return this;
    }

    public AccountValidation trackRole() {
        if (!RoleValidation.track(this.role)) {
            this.errors.add(Message.setInvalid("Role"));
        }
        return this;
    }

    public AccountValidation getEntityBy(String email) {
        this.entity = accountRepository.findByEmail(email).get();
        return this;
    }

    public AccountValidation updatePassword() {
        this.entity.setAccountPassword(new BCryptPasswordEncoder().encode(this.password));
        return this;
    }

    public Account getEntity() {
        Account _Account = new Account();
        _Account.setAccountEmail(this.email);
        _Account.setAccountRole(this.role);
        _Account.setAccountPassword(new BCryptPasswordEncoder().encode(this.password));
        return _Account;
    }

    public boolean save() {
        try {
            this.accountRepository.save(this.entity);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public AccountValidation setEmail(String accountEmail) {
        this.email = accountEmail.trim();
        return this;
    }

    public AccountValidation setPassword(String accountPassword) {
        this.password = accountPassword.trim();
        return this;
    }

    public AccountValidation setRole(String accountRole) {
        this.role = accountRole.trim();
        return this;
    }
}
