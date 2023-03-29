package com.example.demo.entity.account;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}/show")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") String id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    
    // @PostMapping("/store")
    // public  ResponseEntity<?> store(@RequestBody Account account){
    //     try {
    //         accountService.createAccount(account);
    //         return ResponseEntity.ok().build();
    //     } catch (RuntimeException ex) {
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    //     }
    // }
    @PostMapping("/store")
    public  ResponseEntity<?> store(@RequestBody Account account){
        accountService.createAccount(account);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/{id}/update")
    public void updateAccount(@PathVariable("id") String id, @RequestBody Account account) {
        accountService.updateAccount(id, account);
        System.out.println(account);
    }
    

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteAccount(@PathVariable(value = "id") String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }


}
