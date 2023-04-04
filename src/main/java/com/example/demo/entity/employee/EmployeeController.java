package com.example.demo.entity.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.HeadquarterAccountTray;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin("*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public Response getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Response getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
        // -name
        // -age
        // -gender
        // -bỉrthday
        // -phone
        // -email
        // -ID number
        // -chuc vu
        // -noi lam viec
        // -image
    }

    @GetMapping("/{id}/information")
    public Response getInfo(@PathVariable String id) {
        return employeeService.getEmployeeInfo(id);
    }

    @GetMapping("/all-information")
    public Response getAllInfo() {
        return employeeService.getAllEmployeeInfo();
    }

    @PostMapping("/store")
    @Transactional
    public Response createEmployee(
            @RequestBody HeadquarterAccountTray headquarterAccount) {
        return employeeService.storeEmployee(headquarterAccount);
    }

    @PostMapping("/store/multiple")
    public Response createEmployees(@RequestParam("file") MultipartFile file) {
        return employeeService.storeEmployeeFromExcel(file);
    }

    // @PostMapping("/store")
    // public ResponseEntity<EmployeeAccountHeadquarter> createEmployee(
    // @RequestBody EmployeeAccountHeadquarter employeeAccountHeadquarter) {
    // Account account = employeeAccountHeadquarter.getAccount();
    // Employee employee = employeeAccountHeadquarter.getEmployee();
    // Headquarter headquarter = employeeAccountHeadquarter.getHeadquarter();
    // employee.setAccountId(account.getAccountId());
    // employee.setHeadquarterId(headquarter.getHeadquarterId());

    // accountService.storeAccount(account);
    // employeeService.storeEmployee(employee);
    // headquarterService.storeHeadquater(headquarter);

    // return new ResponseEntity<>(employeeAccountHeadquarter, HttpStatus.OK);
    // }

    @PutMapping("/{id}/update")
    public Response updateEmployee(@PathVariable String id,
            @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PutMapping("/{id}/update-self")
    public Response updateEmployeSelf(@PathVariable String id,
            @RequestBody Employee employee) {
        return employeeService.updateSelf(id, employee);
    }

    @DeleteMapping("/{id}/delete")
    public Response deleteEmployee(@PathVariable String id) {
        return employeeService.deleteEmployee(id);
    }

    // @PostMapping("/de")
    // public ResponseEntity<String> createEmployee(@RequestBody EmployeeAccount
    // employeeDTO) {
    // try {
    // // Tạo đối tượng Account từ thông tin trong employeeDTO
    // Account account = new Account();
    // account.setaccountEmail(employeeDTO.getUsername());
    // account.setaccountPassword(employeeDTO.getPassword());
    // account.setaccountRole(employeeDTO.getEmail());
    // account = accountRepository.save(account);

    // // Tạo đối tượng Employee từ thông tin trong employeeDTO và Account vừa tạo
    // Employee employee = new Employee();
    // employee.setHeadquarterId(employeeDTO.getHeadquarterId());
    // employee.setEmployeeName(employeeDTO.getEmployeeName());
    // employee.setEmployeePhone(employeeDTO.getEmployeePhone());
    // employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());
    // employee.setEmployeeGender(employeeDTO.getEmployeeGender());
    // employee.setEmployeePosition(employeeDTO.getEmployeePosition());
    // employee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
    // employee.setAccount(account);
    // employeeRepository.save(employee);

    // return ResponseEntity.ok("Employee created successfully.");
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("Could not create employee: " +
    // e.getMessage());
    // }
    // }

}
