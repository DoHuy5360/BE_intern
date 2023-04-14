package com.example.project_api.entity.email;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.project_api.entity.account.Account;
import com.example.project_api.entity.account.AccountRepository;
import com.example.project_api.response.Message;
import com.example.project_api.response.Response;

@Service
public class EmailService {
    @Autowired private AccountRepository accountRepository;
    
    @Autowired private JavaMailSender mailSender;

    @Autowired private TemplateEngine templateEngine;

    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @Autowired private TokenRandomGenerate tokenRandomGenerate;
    
    public Response sendEmail(String email) throws MessagingException {
        Account accountResetPass = accountRepository.findByEmail(email).orElse(null);
        if (accountResetPass != null) {
            String tokenUUID = "RS-" + TokenRandomGenerate.generateCustomUUID();
            accountResetPass.setResetToken(tokenUUID);
            accountRepository.save(accountResetPass);
            Account accountResetToken = accountRepository.findbyResetToken(tokenUUID).orElse(null);
            if (accountResetToken != null) {
                MimeMessage message = mailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");


                // Tạo nội dung email
                String NewSubject = "Reset Password";
                
                // Tạo text từ template html
                Context thymleafTemplate = new Context();
                thymleafTemplate.setVariable("accountResetToken", accountResetToken.getResetToken());

                String textHTMLContext = templateEngine.process("EmailResetPassword", thymleafTemplate);

                // Tạo email
                helper.setTo(email);
                helper.setSubject(NewSubject);
                helper.setText(textHTMLContext, true);
                
                mailSender.send(message);

                return new Response(HttpStatus.OK, Message.SEND_SUCCESS);
            }
        }
        return new Response(HttpStatus.NOT_FOUND, Message.SEND_FAILED); 
    }


    public Response forgotPasswordForm(String token) {
        Account accountToken = accountRepository.findbyResetToken(token).orElse(null);
        if(accountToken != null) {
            return new Response(HttpStatus.OK, Message.REDIRECT_SUCCESS);
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
        }
    }


    @Transactional
    public Response resetPasswordForm(String token, Account accountReset) {
        Account forgotPassAccount = accountRepository.findbyResetToken(token).orElse(null);
        if(forgotPassAccount != null) {
            forgotPassAccount.setResetToken(null);
            forgotPassAccount.setPassword(this.passwordEncoder.encode(accountReset.getPassword()));
            forgotPassAccount.setRetypePassword(this.passwordEncoder.encode(accountReset.getRetypePassword()));
            if(accountReset.getPassword().equals(accountReset.getRetypePassword())) {
                accountRepository.save(forgotPassAccount);
                return new Response(HttpStatus.OK, Message.CHANGE_SUCCESS);
            }
        }
        return new Response(HttpStatus.OK, Message.CHANGE_FAILED);
    }
}
