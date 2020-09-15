package me.palmseung.demo.accounts.service;

import me.palmseung.demo.accounts.Account;
import me.palmseung.demo.accounts.repository.AccountMapper;
import me.palmseung.demo.accounts.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @DisplayName("Save without @Transacational when GeneratedValue Strategy is Identity")
    @Test
    void saveWithoutTransaction() {
        //given
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();

        //when
        Account savedAccount = accountRepository.save(new Account(username, password));

        //then
        Account selectedByMybatis = accountMapper.selectByUsername(savedAccount.getUsername());
        assertThat(selectedByMybatis).isNotNull();
        assertThat(selectedByMybatis.getUsername()).isEqualTo(username);
        assertThat(selectedByMybatis.getPassword()).isEqualTo(password);
    }

    @DisplayName("Save with @Transacational when GeneratedValue Strategy is Identity")
    @Transactional
    @Test
    void saveWithTransaction() {
        //given
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        //when
        Account savedAccount = accountRepository.save(new Account(username, password));
        Account selectedByMybatis = accountMapper.selectByUsername(savedAccount.getUsername());
        //then
        assertThat(selectedByMybatis).isNotNull();
    }
}
