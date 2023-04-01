package com.example.demo.entity.employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.employee.employee_account.EmployeeAccount;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    // Tao khay lon hon
}
