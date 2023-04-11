package com.example.demo.kit.Query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.kit.TRAY.EmployeeAccountTray;

public interface EmployeeAccountQuery extends CrudRepository<EmployeeAccountTray, String> {
    @Query(value = "select e.employee_id, a.account_role from account a, employee e where a.account_id = e.account_id and account_email = ?1 and account_password = ?2 limit 1", nativeQuery = true)
    List<EmployeeAccountTray> getAccountByEmailPassword(String email, String password);

}
