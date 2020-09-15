package me.palmseung.demo.accounts.repository;

import me.palmseung.demo.accounts.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM account")
    List<Account> select();

    @Select("SELECT * FROM account WHERE username=#{username}")
    Account selectByUsername(@Param("username") String username);

    @Insert("INSERT INTO account(username, password) VALUES(#{username}, #{password})")
    void insert(@Param("username") String username, @Param("password") String password);

    @Delete("DELETE FROM account WHERE id=${id}")
    void delete(@Param("id") Long id);

    @Delete("DELETE account")
    void deleteAll();
}
