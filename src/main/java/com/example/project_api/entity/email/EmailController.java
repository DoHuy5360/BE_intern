package com.example.project_api.entity.email;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_api.entity.account.Account;
import com.example.project_api.response.Response;

@RestController
@RequestMapping("/forgot")
public class EmailController {
    @Autowired private EmailService emailService;

    @PostMapping("/SendEmail")
    public Response forgotPasswordProcess(@RequestParam("email") String email) throws MessagingException {
        return emailService.sendEmail(email);
    }

    @GetMapping("/forgotPassword/{token}")
    public Response forgotPasswordForm(@PathVariable String token) {
        return emailService.forgotPasswordForm(token);
    }

    @PostMapping("/resetPassword/{token}")
    public Response resetPasswordForm(@PathVariable String token ,@RequestBody Account account) {
        return emailService.resetPasswordForm(token, account);
    }
}
