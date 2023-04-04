package com.example.demo.KIT.RES;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeEmailExcelResponse {
    private String email;
    private ArrayList<String> errors;
    private String[] coordinates = new String[2];

    public EmployeeEmailExcelResponse(String email, ArrayList<String> errors, String x, String y) {
        this.email = email;
        this.errors = errors;
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }
}
