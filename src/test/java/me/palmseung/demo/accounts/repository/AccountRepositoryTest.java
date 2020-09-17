package me.palmseung.demo.accounts.repository;

import me.palmseung.demo.accounts.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionManager transactionManager;

    @Test
    void repositoryTest() {
        //given, when
        Account account = new Account("name", "password");
        Account savedAccount = accountRepository.save(account);

        //then
        assertThat(transactionManager.getClass()).isEqualTo(JpaTransactionManager.class);

        assertThat(savedAccount.getId()).isNotNull();
        assertThat(savedAccount.getUsername()).isEqualTo("name");
        assertThat(savedAccount.getPassword()).isEqualTo("password");
    }
}
