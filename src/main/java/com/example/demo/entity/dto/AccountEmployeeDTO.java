package com.example.demo.entity.dto;

import com.example.demo.entity.account.Account;
import com.example.demo.entity.employee.Employee;

import lombok.Data;

@Data
public class AccountEmployeeDTO {
    private String accountId;
    private String accountEmail;
    private String accountPassword;
    private String accountRole;
    private String employeeId;
    private String headquarterId;
    private String employeeName;
    private String employeePhone;
    private String employeeAddress;
    private String employeeGender;
    private String employeePosition;
    private int employeeSalary;


    

    public AccountEmployeeDTO(Account account, Employee employee) {
        this.accountId = account.getAccountId();
        this.accountEmail = account.getAccountEmail();
        this.accountPassword = account.getAccountPassword();
        this.accountRole = account.getAccountRole();
        this.employeeId = employee.getEmployeeId();
        this.headquarterId = employee.getHeadquarterId();
        this.employeeName = employee.getEmployeeName();
        this.employeePhone = employee.getEmployeePhone();
        this.employeeAddress = employee.getEmployeeAddress();
        this.employeeGender = employee.getEmployeeGender();
        this.employeePosition = employee.getEmployeePosition();
        this.employeeSalary = employee.getEmployeeSalary();
    }

  
}

