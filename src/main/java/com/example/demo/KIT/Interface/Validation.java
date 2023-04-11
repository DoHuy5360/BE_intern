package com.example.demo.kit.Interface;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.entity.employee.Employee;

public abstract class Validation {
    public ArrayList<String> errors = new ArrayList<String>();
    public Optional<Employee> employeeFound;

    public boolean isValid() {
        return (this.errors.isEmpty()) ? true : false;
    }

    public Employee get() {
        return employeeFound.get();
    }

    public ArrayList<String> getErrors() {
        return this.errors;
    }

    public int getAmountErrors() {
        return this.errors.size();
    }
}
