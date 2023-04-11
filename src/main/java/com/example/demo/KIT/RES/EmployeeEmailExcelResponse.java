package com.example.demo.kit.res;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeEmailExcelResponse {
    private ArrayList<String> errors;
    private int row;

    public EmployeeEmailExcelResponse(ArrayList<String> errors, int row) {
        this.row = row;
        this.errors = errors;
    }
}
