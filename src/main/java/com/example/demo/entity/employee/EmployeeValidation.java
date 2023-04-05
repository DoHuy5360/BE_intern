package com.example.demo.entity.employee;

import com.example.demo.KIT.Interface.Validation;
import com.example.demo.KIT.RES.Message;
import com.example.demo.entity.headquarter.HeadquarterRepository;

public class EmployeeValidation extends Validation {
    private String employeeId;
    private Employee employee;
    private EmployeeRepository employeeRepository;

    private HeadquarterRepository headquarterRepository;

    public EmployeeValidation(String employeeId, Employee employee, EmployeeRepository employeeRepository,
            HeadquarterRepository headquarterRepository) {
        this.employeeId = employeeId;
        this.employee = employee;
        this.employeeRepository = employeeRepository;
        this.headquarterRepository = headquarterRepository;
        this.employeeFound = this.employeeRepository.findById(this.employeeId);
    }

    public EmployeeValidation trackIdExist() {
        if (employeeFound.isEmpty()) {
            this.errors.add(Message.setNotExistMessage("Employee ID"));
        }
        return this;
    }

    public EmployeeValidation trackHeadquarterId() {
        String headquarterId = this.employee.getHeadquarterId();
        if (headquarterRepository.findById(headquarterId).isEmpty())
            this.errors.add(Message.setNotExistMessage("Headquarter ID"));
        return this;
    }

    public EmployeeValidation trackNumberLenghtEqual() {
        String pattern = "\\d{10}";
        if (!this.employee.getEmployeePhone().matches(pattern)) {
            this.errors.add(Message.setInvalid("Phone number"));
        }
        return this;
    }
}
