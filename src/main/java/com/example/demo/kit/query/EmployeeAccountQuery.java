package com.example.demo.kit.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.kit.tray.EmployeeAccountTray;

@Repository
public interface EmployeeAccountQuery extends CrudRepository<EmployeeAccountTray, String> {
    @Query(value = "select * from employee e inner join account a on e.account_id = a.account_id where e.employee_id = ?1 limit 1", nativeQuery = true)
    List<EmployeeAccountTray> getAccountByEmployeeId(String employeeId);

    @Query(value = "select e.employee_id, a.account_role, a.account_password, a.account_email from account a, employee e where a.account_id = e.account_id and account_email = ?1 limit 1", nativeQuery = true)
    List<EmployeeAccountTray> getAccountByEmailPassword(String email);

    @Query(value = "select employee_id, a.account_role, a.account_password, a.account_email from employee e, account a  where e.account_id = a.account_id and e.employee_id = ?1", nativeQuery = true)
    List<EmployeeAccountTray> findPasswordEmployee(String employeeId);
}
