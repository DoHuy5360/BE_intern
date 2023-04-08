package com.example.project_api.entity.email;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.project_api.entity.account.Account;
import com.example.project_api.entity.account.AccountRepository;

@RestController
@RequestMapping("/forgot")
public class EmailController {
    @Autowired private JavaMailSender mailSender;

    @Autowired private TemplateEngine templateEngine;

    @Autowired private AccountRepository accountRepository;

    @PostMapping("/forgotPassword/{email}")
    public ResponseEntity<String> forgotPasswordProcess(@PathVariable String email, @RequestBody String to, String subject, String text) throws MessagingException {
        Account accountResetPass = accountRepository.findByEmail(email).orElse(null);
        if (accountResetPass != null) {
            String token = UUID.randomUUID().toString();
            accountResetPass.setResetToken(token);
            accountRepository.save(accountResetPass);
            Account accountResetToken = accountRepository.findbyResetToken(token).orElse(null);
            if (accountResetToken != null) {
                MimeMessage message = mailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

                // Tạo nội dung email
                String NewSubject = "Reset Password";
                
                // Tạo text từ template html
                Context thymleafTemplate = new Context();
                thymleafTemplate.setVariable("accountResetToken", accountResetToken);

                String textHTMLContext = templateEngine.process("EmailResetPassword", thymleafTemplate);

                // Tạo email
                helper.setTo(email);
                helper.setSubject(NewSubject);
                helper.setText(textHTMLContext, true);
                
                mailSender.send(message);
            }
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
