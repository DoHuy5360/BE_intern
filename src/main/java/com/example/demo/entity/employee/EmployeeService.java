package com.example.demo.entity.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.account.AccountRepository;
import com.example.demo.entity.employee.employee_account.EmployeeAccount;
import com.example.demo.entity.employee.employee_account.EmployeeAccountRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeAccountRepository employeeAccountRepository;

    public List<Employee> getAllEmployee() {
        System.out.println("From getAllEmployee()");
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployeeById(String EmployeeUserId) {
        System.out.println("From getEmployeeById()");
        Optional<Employee> one_E = employeeRepository.findById(EmployeeUserId);
        return one_E.orElse(null);
    }

    public Employee storeEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public ResponseEntity<Employee> updateEmployee(String employeeId, Employee employee) {
        System.out.println("From updateEmployee()");
        Optional<Employee> updatEmployeeExist = employeeRepository.findById(employeeId);
        if (updatEmployeeExist.isPresent()) {
            Employee _Employee = updatEmployeeExist.get();
            _Employee.setHeadquarterId(employee.getHeadquarterId());
            _Employee.setEmployeeName(employee.getEmployeeName());
            _Employee.setEmployeePhone(employee.getEmployeePhone());
            _Employee.setEmployeeAddress(employee.getEmployeeAddress());
            _Employee.setEmployeeGender(employee.getEmployeeGender());
            _Employee.setEmployeePosition(employee.getEmployeePosition());
            _Employee.setEmployeeSalary(employee.getEmployeeSalary());
            return new ResponseEntity<>(employeeRepository.save(_Employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteEmployee(String id) {
        System.out.println("From deleteEmployee()");
        Optional<Employee> oneEm = employeeRepository.findById(id);
        if (oneEm.isPresent()) {
            Employee _Employee = oneEm.get();
            employeeRepository.deleteById(id);
            accountRepository.deleteById(_Employee.getAccountId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<EmployeeAccount> getEmployeeInfo(String id) {
        System.out.println("From getEmployeeInfo()");
        return new ResponseEntity<>(employeeAccountRepository.getInformation(id).get(), HttpStatus.OK);
    }
}
