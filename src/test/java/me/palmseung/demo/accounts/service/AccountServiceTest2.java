package me.palmseung.demo.accounts.service;

import me.palmseung.demo.accounts.Account;
import me.palmseung.demo.accounts.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountServiceTest2 {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void saveByBothTest() {
        //given
        Account accountForJpa = new Account("jpa", "passwordjpa");
        Account accountForMybatis = new Account("mybatis", "passwordmybatis");

        //when
        accountService.saveByBoth(accountForJpa, accountForMybatis);

        //then
        Account jpaAccount = accountRepository.findByUsername(accountForJpa.getUsername()).get();
        assertThat(jpaAccount).isEqualTo(accountForJpa);

        Account mybatisAccount = accountRepository.findByUsername(accountForMybatis.getUsername()).get();
        assertThat(mybatisAccount).isEqualTo(accountForMybatis);
    }

    @Test
    void saveByBothWithExceptionTest() {
        //given
        Account accountForJpa = new Account(UUID.randomUUID().toString(), "passwordjpa");
        Account accountForMybatis = new Account(UUID.randomUUID().toString(), "passwordmybatis");

        //when
        assertThatThrownBy(() -> {
            accountService.saveByBothWithException(accountForJpa, accountForMybatis);
        }).isInstanceOf(RuntimeException.class);


        //then
        Optional<Account> jpaAccount = accountRepository.findByUsername(accountForJpa.getUsername());
        assertThat(jpaAccount).isEqualTo(Optional.empty());

        Optional<Account> mybatisAccount = accountRepository.findByUsername(accountForMybatis.getUsername());
        assertThat(mybatisAccount).isEqualTo(Optional.empty());
    }
}
