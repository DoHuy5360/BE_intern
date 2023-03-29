package com.example.demo.entity.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.utilities.Time;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> one_Employee = employeeRepository.findById(id);
        return one_Employee.orElse(null);
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public ResponseEntity<Employee> updateEmployee(String id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
    
        if (!optionalEmployee.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Employee existingEmployee = optionalEmployee.get();
    
        existingEmployee.setHeadquarterId(employee.getHeadquarterId());
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setEmployeePhone(employee.getEmployeePhone());
        existingEmployee.setEmployeeAddress(employee.getEmployeeAddress());
        existingEmployee.setEmployeeGender(employee.getEmployeeGender());
        existingEmployee.setEmployeePosition(employee.getEmployeePosition());
        existingEmployee.setEmployeeSalary(employee.getEmployeeSalary());
        existingEmployee.setUpdateAt(Time.getCurrentDate());




        existingEmployee.setUpdateAt(Time.getCurrentDate());
    
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
    
        return ResponseEntity.ok(updatedEmployee);
    }

    public void deleteEmployeee(String id) {
        employeeRepository.deleteById(id);
    }


}
