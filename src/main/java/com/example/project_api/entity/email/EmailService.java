package com.example.project_api.entity.email;

import org.springframework.mail.javamail.MimeMessagePreparator;

import com.example.project_api.entity.account.Account;

public interface EmailService {
    public void sendEmail(MimeMessagePreparator email);

    public void forgotPasswordProcess(String emailResetPass, Account account);
}
