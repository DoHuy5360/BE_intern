package com.example.demo.entity.employee;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Optional<Employee> findByAccountId(String accountId);
    
}
