package com.example.project_api.entity.email;

import org.springframework.mail.javamail.MimeMessagePreparator;

import com.example.project_api.entity.account.Account;

public class EmailServiceImp implements EmailService {
    @Override
    public void forgotPasswordProcess(String emailResetPass, Account account) {}

    @Override
    public void sendEmail(MimeMessagePreparator email) {}
    
}
