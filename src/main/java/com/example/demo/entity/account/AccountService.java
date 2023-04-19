package com.example.demo.entity.account;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.kit.jwt.JwtEmailCertificateFormat;
import com.example.demo.kit.jwt.JwtHandler;
import com.example.demo.kit.query.EmployeeAccountQuery;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountTray;
import com.example.demo.kit.validation.AccountValidation;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeAccountQuery employeeAccountQuery;

    @Autowired
    private JwtHandler jwtHandler;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public List<EmployeeAccountTray> checkLogin(String email) {
        return employeeAccountQuery.getAccountByEmailPassword(email);
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
    public Response resetPassword(String accountEmail, Account account) {
        AccountValidation accountValidation = new AccountValidation(account, accountRepository)
                .setEmail(accountEmail)
                .trackEmailExist()
                .trackEmailFormat()
                .trackPasswordFormat()
                .trackPasswordRetype();
        if (accountValidation.isValid()) {
            return (accountValidation.getEntityBy(accountEmail).updatePassword().save())
                    ? new Response(HttpStatus.OK, Message.UPDATE_SUCCESS)
                    : new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
        } else {
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    Message.INVALID,
                    accountValidation.getAmountErrors(),
                    accountValidation.getErrors());
        }
    }

    public Response changePassword(String accountEmail, Account account) {

        AccountValidation accountValidation = new AccountValidation(account, accountRepository)
                .setEmail(accountEmail)
                .trackEmailExist()
                .trackEmailFormat()
                .trackPasswordFormat()
                .trackPasswordRetype();
        if (accountValidation.isValid()) {
            return (accountValidation.getEntityBy(accountEmail).updatePassword().save())
                    ? new Response(HttpStatus.OK, Message.UPDATE_SUCCESS)
                    : new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
        } else {
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    Message.INVALID,
                    accountValidation.getAmountErrors(),
                    accountValidation.getErrors());
        }
    }

    public Response forgotPassword(Account account) {

        Optional<Account> oneA = accountRepository.findByEmail(account.getAccountEmail());
        if (oneA.isPresent()) {

            final int MINUTE = 5;
            final int SECOND = 60;
            final int MILLISECOND = 1000;
            JwtEmailCertificateFormat jwtBoolFormat = new JwtEmailCertificateFormat(true, account.getAccountEmail());
            ObjectMapper convertJson = new ObjectMapper();
            String token;
            try {
                token = convertJson.writeValueAsString(jwtBoolFormat);
            } catch (Exception e) {
                token = null;
            }
            String jwtToken = jwtHandler.generateToken(token, MINUTE * SECOND * MILLISECOND);

            Context thymleafTemplate = new Context();
            thymleafTemplate.setVariable("jwtToken", jwtToken);
            thymleafTemplate.setVariable("url", "https://be-intern.onrender.com/assets/html/changePassword.html");

            String textHTMLContext = templateEngine.process("changePasswordForm", thymleafTemplate);

            MimeMessage message = mailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                String NewSubject = "Reset Password";
                helper.setTo(account.getAccountEmail());
                helper.setSubject(NewSubject);
                helper.setText(textHTMLContext, true);
                mailSender.send(message);
            } catch (MessagingException e) {
                System.out.println(e);
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.setFailMessage("Mail delivery"));
            }

            return new Response(HttpStatus.OK, "Please Check Mail Box.");
        } else {
            return new Response(HttpStatus.BAD_REQUEST, Message.setInvalid("Email"));

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