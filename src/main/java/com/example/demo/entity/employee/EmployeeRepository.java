package com.example.demo.entity.employee;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.account.Account;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Optional<Employee> findByAccountId(String accountId);
    
}
