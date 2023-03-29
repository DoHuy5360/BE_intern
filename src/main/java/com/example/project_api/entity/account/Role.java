package com.example.project_api.entity.account;

public enum Role {
    ADMIN("admin"),USER("user");

    private String role;

    Role(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
    
}
