package com.example.demo.entity.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/{id}/show")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") String id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }


    @PostMapping("/store")
    public  ResponseEntity<?> store(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/update")
    public void updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        System.out.println(employee);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") String id) {
        employeeService.deleteEmployeee(id);
        return ResponseEntity.noContent().build();
    }

}
