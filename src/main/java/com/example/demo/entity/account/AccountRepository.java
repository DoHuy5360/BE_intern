package com.example.demo.entity.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
    @Query(value = "SELECT * FROM account c WHERE c.email = ?1 ", nativeQuery = true)
    Optional<Account> findByEmail(String email);
}
