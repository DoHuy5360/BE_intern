package com.example.demo.entity.dto;

import com.example.demo.entity.account.Account;
import com.example.demo.entity.employee.Employee;

public class EmployeeDTO {
    private String headquarterId;
    private String accountEmail;
    private String accountRole;
    private String employeeName;
    private String employeePhone;
    private String employeeAddress;
    private String employeeGender;
    private String employeePosition;
    private int employeeSalary;

    public EmployeeDTO() {}

    public EmployeeDTO(String headquarterId, String accountEmail, String accountRole, String employeeName, String employeePhone, String employeeAddress, String employeeGender, String employeePosition, int employeeSalary) {
        this.headquarterId = headquarterId;
        this.accountEmail = accountEmail;
        this.accountRole = accountRole;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeAddress = employeeAddress;
        this.employeeGender = employeeGender;
        this.employeePosition = employeePosition;
        this.employeeSalary = employeeSalary;
    }

    public String getHeadquarterId() {
        return headquarterId;
    }

    public void setHeadquarterId(String headquarterId) {
        this.headquarterId = headquarterId;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(String accountRole) {
        this.accountRole = accountRole;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeName(this.getEmployeeName());
        employee.setEmployeePhone(this.getEmployeePhone());
        employee.setEmployeeAddress(this.getEmployeeAddress());
        employee.setEmployeeGender(this.getEmployeeGender());
        employee.setEmployeePosition(this.getEmployeePosition());
        employee.setEmployeeSalary(this.getEmployeeSalary());
        employee.setHeadquarterId(this.getHeadquarterId());
        return employee;
    }

    public Object getEmail() {
        return null;
    }

    public void setAccount(Account account) {
    }
}
