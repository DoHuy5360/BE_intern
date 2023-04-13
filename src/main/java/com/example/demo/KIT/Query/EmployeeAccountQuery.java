package com.example.demo.kit.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.kit.tray.EmployeeAccountTray;

public interface EmployeeAccountQuery extends CrudRepository<EmployeeAccountTray, String> {
    // @Query(value = "select e.employee_id, a.account_role from account a, employee
    // e where a.account_id = e.account_id and account_email = ?1 and
    // account_password = ?2 limit 1", nativeQuery = true)
    // List<EmployeeAccountTray> getAccountByEmailPassword(String email, String
    // password);

    @Query(value = "select e.employee_id, a.account_role, a.account_password, a.account_email from account a, employee e where a.account_id = e.account_id and account_email = ?1 limit 1", nativeQuery = true)
    List<EmployeeAccountTray> getAccountByEmailPassword(String email);

}
