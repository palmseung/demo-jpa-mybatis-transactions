package me.palmseung.demo.accounts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.palmseung.demo.accounts.Account;
import me.palmseung.demo.accounts.repository.AccountMapper;
import me.palmseung.demo.accounts.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public void save(Account account) {
        System.out.println("before");
        Account save = accountRepository.saveAndFlush(account);
        System.out.println("after");

        accountMapper.insert("heyheyhey", "password");
        log.info("2.");

        Account savedAccount = accountRepository.findByUsername(save.getUsername()).get();
        log.info("3.savedByMybatisThenSelectedByJpa {} : ", savedAccount);

        Account selectedAccount = accountMapper.selectByUsername(save.getUsername());
        log.info("4.accountSelectedByMybatis: {}", selectedAccount);

        Account findById = accountRepository.findById(save.getId()).get();
        log.info("5.accountSelectedByJpa {}", findById);
    }


}
