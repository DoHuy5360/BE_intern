package com.example.demo.entity.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    @Query(value = "SELECT * FROM account c WHERE c.account_email = ?1 ", nativeQuery = true)
    Optional<Account> findByEmail(String email);

    @Query(value = "select * from account where account_email = ?1", nativeQuery = true)
    List<Account> getAccountByEmail(String email);

    @Query(value = "select * from account where account_email = ?1 and account_password = ?2 limit 1", nativeQuery = true)
    List<Account> getAccountByEmailPassword(String email, String password);

}
