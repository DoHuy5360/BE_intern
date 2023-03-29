package com.example.project_api.entity.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String EmployeeUserId) {
        return employeeRepository.findById(EmployeeUserId);
    }

    public Employee createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployee(String employeeId, Employee employee) {
        Employee updatEmployeeExist = getEmployeeById(employeeId).orElse(employee);
        if (updatEmployeeExist != null) {
            updatEmployeeExist.setHeadquarterId(employee.getHeadquarterId());
            updatEmployeeExist.setEmployeeName(employee.getEmployeeName());
            updatEmployeeExist.setEmployeePhone(employee.getEmployeePhone());
            updatEmployeeExist.setEmployeeAddress(employee.getEmployeeAddress());
            updatEmployeeExist.setEmployeeGender(employee.getEmployeeGender());
            updatEmployeeExist.setEmployeePosition(employee.getEmployeePosition());
            updatEmployeeExist.setEmployeeSalary(employee.getEmployeeSalary());
            employeeRepository.save(updatEmployeeExist);
        }
        return updatEmployeeExist;
    }

    public Employee deleteEmployee(String employeeId, Employee employee) {
        employeeRepository.deleteById(employeeId);
        return employee;
    }
}
