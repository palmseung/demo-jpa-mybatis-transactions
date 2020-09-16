package me.palmseung.demo.accounts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.palmseung.demo.accounts.Account;
import me.palmseung.demo.accounts.repository.AccountMapper;
import me.palmseung.demo.accounts.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public void saveByBoth(Account accountForJpa, Account accountForMybatis) {
        accountRepository.save(accountForJpa);
        accountMapper.insert(accountForMybatis.getUsername(), accountForMybatis.getPassword());
    }

    @Transactional
    public void saveByBothWithException(Account accountForJpa, Account accountForMybatis) {
        accountRepository.save(accountForJpa);
        accountMapper.insert(accountForMybatis.getUsername(), accountForMybatis.getPassword());

        throw new RuntimeException();
    }

    @Transactional
    public void saveByMapper() {
        accountMapper.insert(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        accountMapper.insert(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        accountMapper.insert(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        accountMapper.insert(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        throw new RuntimeException();
    }

    @Transactional
    public void saveByJpa() {
        System.out.println("메소드 시작");
        accountRepository.save(new Account(UUID.randomUUID().toString(), UUID.randomUUID().toString()));

        System.out.println("메소드 실행 중");
        accountRepository.save(new Account(UUID.randomUUID().toString(), UUID.randomUUID().toString()));

        System.out.println("메소드 실행 중");
        accountRepository.save(new Account(UUID.randomUUID().toString(), UUID.randomUUID().toString()));

        System.out.println("메소드 종료");
    }

    @Transactional
    public void save() {
        Account save = accountRepository.save(new Account("jpa", "password"));
        save.setUsername("modified");
        accountMapper.insert(save.getUsername() + "byMybatis", "password");
    }
}
