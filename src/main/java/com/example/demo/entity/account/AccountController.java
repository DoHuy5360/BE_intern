package com.example.demo.entity.account;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.kit.res.Response;
import com.example.demo.kit.util.DiscordLogger;

@RestController
@RequestMapping("/api/v2/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    DiscordLogger discordLogger;

    @GetMapping("/")
    public Response index() {
        // Page
        return accountService.getAllAccount();

    }

    @GetMapping("/{id}/show")
    public Response show(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @PostMapping("/store")
    public Response store(@RequestBody Account account) {

        return accountService.storeAccount(account);
    }

    @PutMapping("/{id}/update")
    public Response updateAccount(@PathVariable String id, @RequestBody Account account, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Update account.");
        return accountService.updateAccount(id, account);

    }

    @PutMapping("/reset-password")
    public Response reset(@RequestBody Account account, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Reset password.");
        String accountEmail = (String) request.getAttribute("AccountEmail");
        return accountService.resetPassword(accountEmail, account);
    }

    @PutMapping("/change-password")
    public Response change(@RequestBody Account account, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Change password.");
        String accountEmail = (String) request.getAttribute("AccountEmail");
        return accountService.changePassword(accountEmail, account);
    }

    @DeleteMapping("/{id}/delete")
    public Response deleteAccount(@PathVariable String id, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Delete account.");
        return accountService.deleteAccount(id);
    }

    @PostMapping("/forgot-password")
    public Response forgetPasswork(HttpServletRequest request, @RequestBody Account account) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Forgot password.");
        return accountService.forgotPassword(account);

    }

    @PostMapping("/multiple-account")
    public Response multipleStore(@RequestParam("file") MultipartFile file) {
        long startTime = System.nanoTime();

        Response result = accountService.multipleStoreAccount(file);

        long endTime = System.nanoTime();
        long durationInSeconds = (endTime - startTime) / 1000000000;
        discordLogger.no1Send("Tester",
                String.format("Has implemented multiple storing -> total cost %s sec.",
                        durationInSeconds));
        return result;
    }

}
