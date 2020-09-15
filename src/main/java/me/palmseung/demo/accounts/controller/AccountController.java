package me.palmseung.demo.accounts.controller;

import lombok.RequiredArgsConstructor;
import me.palmseung.demo.accounts.Account;
import me.palmseung.demo.accounts.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public void saveAccount() {
        Account account = new Account(UUID.randomUUID().toString(), "password");
        accountService.save(account);
    }
}
