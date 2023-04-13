package com.example.demo.kit.jwt;

public class JwtFormat {
    public String employeeId;
    public String accountRole;

    public JwtFormat(String employeeId, String accountRole) {
        this.employeeId = employeeId;
        this.accountRole = accountRole;
    }
}
