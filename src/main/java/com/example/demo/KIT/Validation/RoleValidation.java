package com.example.demo.KIT.Validation;

import java.util.Arrays;
import java.util.List;

public class RoleValidation {
    public static List<String> roles = Arrays.asList("Admin", "User");

    public static boolean track(String role) {
        return (roles.contains(role)) ? true : false;
    }
}
