package com.example.demo.entity.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kit.RES.Response;

@RestController
@RequestMapping("/api/v2/account")
public class AccountController {
    @Autowired
    AccountService accountService;

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
    public Response updateAccount(@PathVariable String id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);

    }

    @PutMapping("/reset-password")
    public Response reset(@RequestBody Account account) {
        return accountService.resetPassword(account);
    }

    @DeleteMapping("/{id}/delete")
    public Response deleteAccount(@PathVariable String id) {
        return accountService.deleteAccount(id);
    }
}
