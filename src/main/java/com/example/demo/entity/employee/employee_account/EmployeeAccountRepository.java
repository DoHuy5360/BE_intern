package com.example.demo.entity.employee.employee_account;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAccountRepository extends CrudRepository<EmployeeAccount, String> {
    @Query(value = "SELECT * FROM employee e, account a WHERE e.employee_id = ?1 AND e.account_id = a.account_id", nativeQuery = true)
    Optional<EmployeeAccount> getInformation(String employeeId);
}
