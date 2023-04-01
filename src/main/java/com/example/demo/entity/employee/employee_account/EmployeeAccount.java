package com.example.demo.entity.employee.employee_account;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeAccount {
    @Id
    private String employeeId;

    private String headquarterId;

    private String employeeName;

    private String employeePhone;

    private String employeeAddress;

    private String employeeGender;

    private String employeePosition;

    private int employeeSalary;

    private String accountId;

    private String accountEmail;

    private String accountPassword;

    private String accountRole;

    private String createAt;

    private String updateAt;

}