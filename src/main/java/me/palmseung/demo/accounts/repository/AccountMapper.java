package me.palmseung.demo.accounts.repository;

import me.palmseung.demo.accounts.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM account WHERE username=#{username}")
    Account selectByUsername(@Param("username") String username);

    @Insert("INSERT INTO account(username, password) VALUES(#{username}, #{password})")
    void insert(@Param("username") String username, @Param("password") String password);
}
