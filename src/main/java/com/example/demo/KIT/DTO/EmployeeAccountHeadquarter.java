package com.example.demo.kit.dto;

import com.example.demo.entity.account.Account;
import com.example.demo.entity.employee.Employee;
import com.example.demo.entity.headquarter.Headquarter;

import lombok.Data;

@Data
public class EmployeeAccountHeadquarter {
    private Employee employee;
    private Account account;
    private Headquarter headquarter;
}
