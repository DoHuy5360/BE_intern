package com.example.demo.entity.account;

public class Role {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
