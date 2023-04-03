package com.example.demo.entity.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.KIT.Query.EmployeeAccountHeadquarterQuery;
import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.EmployeeAccountHeadquarterTray;
import com.example.demo.entity.account.AccountRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeAccountHeadquarterQuery employeeAccountRepository;

    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployeeById(String EmployeeUserId) {
        Optional<Employee> one_E = employeeRepository.findById(EmployeeUserId);
        return one_E.orElse(null);
    }

    public Employee storeEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public ResponseEntity<Employee> updateEmployee(String employeeId, Employee employee) {
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

    public Response getEmployeeInfo(String id) {
        Optional<EmployeeAccountHeadquarterTray> oneE = employeeAccountRepository.getInformation(id);
        if (oneE.isPresent()) {
            return new Response(HttpStatus.OK, Message.READ_SUCCESS, oneE);
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);

        }
    }
}
