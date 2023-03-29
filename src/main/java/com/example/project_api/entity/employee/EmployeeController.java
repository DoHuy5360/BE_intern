package com.example.project_api.entity.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
        Optional<Employee> GetemployeeId = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(GetemployeeId.orElse(null), HttpStatus.OK);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee NewEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(NewEmployee, HttpStatus.OK);
    }

    @PutMapping("/{employeeId}/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) {
        Employee updateEmployeeExist = employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(updateEmployeeExist, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}/deleteEmployee")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable String employeeId, Employee employee) {
        Employee deleteEmployeeExist = employeeService.deleteEmployee(employeeId, employee);
        return new ResponseEntity<>(deleteEmployeeExist, HttpStatus.OK);
    }
}
