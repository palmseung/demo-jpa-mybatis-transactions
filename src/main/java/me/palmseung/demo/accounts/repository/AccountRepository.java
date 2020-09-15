package me.palmseung.demo.accounts.repository;

import me.palmseung.demo.accounts.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(@Param("username") String username);
}
