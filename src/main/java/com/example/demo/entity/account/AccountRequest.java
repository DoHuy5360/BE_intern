package com.example.demo.entity.account;

public class AccountRequest {
    private String accountEmail;
    private String accountPassword;
    private String accountRole;
    private String headquarterId ;
    private String employeeName;
    private String employeePhone;
    private String employeeAddress;
    private String employeeGender;
    private String employeePosition;
    private int employeeSalary;

    // add other fields as necessary
    
    // add getters and setters
    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(String accountRole) {
        this.accountRole = accountRole;
    }

    public String getHeadquarterId() {
        return headquarterId;
    }
    public void setHeadquarterId(String headquarterId) {
        this.headquarterId = headquarterId;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }
    
    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
    
    public String getEmployeeAddress() {
        return employeeAddress;
    }
    
    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }
    public String getEmployeeGender() {
        return employeeGender;
    }
    
    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }
    
    public String getEmployeePosition() {
        return employeePosition;
    }
    
    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
    public int getEmployeeSalary() {
        return employeeSalary;
    }
}
