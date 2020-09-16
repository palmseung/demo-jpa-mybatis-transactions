package me.palmseung.demo.accounts.controller;

import lombok.RequiredArgsConstructor;
import me.palmseung.demo.accounts.Account;
import me.palmseung.demo.accounts.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public String saveAccount() {
        accountService.save();

        return "created!";
    }

    @GetMapping(value = "/mapper")
    public String saveAccountByMapper() {
        accountService.saveByMapper();

        return "created!";
    }

    @GetMapping(value = "/jpa")
    public String saveAccountByJpa() {
        accountService.saveByJpa();

        return "created!";
    }
}
