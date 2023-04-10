package com.example.demo.entity.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
@EnableJpaRepositories
@Repository
public interface AccountRepository extends  JpaRepository<Account, String> {
    @Query(value = "SELECT * FROM account c WHERE c.account_email = ?1 ", nativeQuery = true)
    Optional<Account> findByEmail(String email);

}
