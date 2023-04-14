package com.example.demo.kit.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.entity.employee.Employee;
import com.example.demo.entity.employee.EmployeeRepository;
import com.example.demo.entity.headquarter.HeadquarterRepository;
import com.example.demo.kit.res.Message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeValidation extends PrimitiveValidation {
    // private String employeeId;
    private Employee employee;
    private EmployeeRepository employeeRepository;
    private HeadquarterRepository headquarterRepository;
    public String employeeName;

    public EmployeeValidation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeValidation(String employeeId, Employee employee, EmployeeRepository employeeRepository,
            HeadquarterRepository headquarterRepository) {
        this.entityId = employeeId;
        this.employee = employee;
        this.employeeRepository = employeeRepository;
        this.headquarterRepository = headquarterRepository;
        this.employeeName = this.employee.getEmployeeName();
    }

    /**
     * Validate for employeeId -
     * Reiquire employeeId exist in database
     * 
     * @return this
     */
    public EmployeeValidation trackIdExist() {
        this.employeeFound = this.employeeRepository.findById(this.entityId);
        if (employeeFound.isEmpty()) {
            this.errors.add(Message.setNotExistMessage("Employee ID"));
        }
        return this;
    }

    /**
     * @return this
     */
    public EmployeeValidation trackHeadquarterId() {
        String headquarterId = this.employee.getHeadquarterId();
        if (headquarterRepository.findById(headquarterId).isEmpty())
            this.errors.add(Message.setNotExistMessage("Headquarter ID"));
        return this;
    }

    /**
     * @return this
     */
    public EmployeeValidation trackPhoneLength() {
        String pattern = "\\d{10}";
        if (!this.employee.getEmployeePhone().matches(pattern)) {
            this.errors.add(Message.setInvalid("Phone number"));
        }
        return this;
    }

    /**
     * @return this
     */
    public EmployeeValidation trackGenderLength() {
        String pattern = "\\d{1}";
        if (!this.employee.getEmployeeGender().matches(pattern)) {
            this.errors.add(Message.setInvalid("Gender value"));
        }
        return this;
    }

    public EmployeeValidation trackName() {
        if (isBlank(this.employeeName)) {
            this.errors.add(Message.setEmptyMessage("Name"));
        }
        if (isMatchMinMaxLength(this.employeeName, 1, 25)) {
            this.errors.add(Message.setInvalid("Name length"));
        }
        return this;
    }

    public EmployeeValidation setId(String employeeId) {
        this.entityId = employeeId;
        return this;

    }

    public EmployeeValidation isValidEmployeeId() {
        if (!isValidId("NV")) {
            this.errors.add(Message.setInvalid("Employee ID"));
        }
        return this;
    }
}
