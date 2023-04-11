package com.example.demo.kit.validation;

import java.util.Arrays;
import java.util.List;

import com.example.demo.config.middleware.auth.KIT.Role;

public class RoleValidation {
    public static List<String> roles = Arrays.asList(Role.MANAGER, Role.EMPLOYEE);

    public static boolean track(String role) {
        return (roles.contains(role)) ? true : false;
    }
}
