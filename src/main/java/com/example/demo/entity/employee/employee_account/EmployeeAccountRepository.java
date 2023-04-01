package com.example.demo.entity.employee.employee_account;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAccountRepository extends CrudRepository<EmployeeAccount, String> {
    @Query(value = "SELECT employee_id, e.account_id, e.headquarter_id, employee_name, employee_phone, employee_address, employee_gender, employee_position, employee_salary, account_email, account_role, headquarter_name, headquarter_address FROM employee e, account a , headquarter h WHERE e.employee_id = ?1 AND e.account_id = a.account_id AND e.headquarter_id = h.headquarter_id", nativeQuery = true)
    Optional<EmployeeAccount> getInformation(String employeeId);
}
