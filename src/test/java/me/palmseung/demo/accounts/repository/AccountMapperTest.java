package me.palmseung.demo.accounts.repository;

import me.palmseung.demo.accounts.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@MybatisTest
@MapperScan(value = "me.palmseung.demo")
@Sql({"classpath:schema.sql"})
public class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TransactionManager transactionManager;

    @Test
    void insertAndSelectTest() {
        //given
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();

        //when
        accountMapper.insert(username, password);

        //then
        assertThat(transactionManager.getClass()).isEqualTo(DataSourceTransactionManager.class);

        Account savedAccount = accountMapper.selectByUsername(username);
        assertThat(savedAccount.getId()).isNotNull();
        assertThat(savedAccount.getUsername()).isEqualTo(username);
        assertThat(savedAccount.getPassword()).isEqualTo(password);
    }
}
