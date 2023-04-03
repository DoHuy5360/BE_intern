package com.example.demo.entity.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Response getAllEmployee() {
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, (List<Employee>) employeeRepository.findAll());
    }

    public Response getEmployeeById(String EmployeeUserId) {
        Optional<Employee> one_E = employeeRepository.findById(EmployeeUserId);
        return (one_E.isPresent()) ? new Response(HttpStatus.OK, Message.READ_SUCCESS, one_E.get())
                : new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
    }

    public Employee storeEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public Response updateEmployee(String employeeId, Employee employee) {
        Optional<Employee> oneE = employeeRepository.findById(employeeId);
        if (oneE.isPresent()) {
            try {
                Employee _Employee = oneE.get();
                _Employee.setHeadquarterId(employee.getHeadquarterId());
                _Employee.setEmployeeName(employee.getEmployeeName());
                _Employee.setEmployeePhone(employee.getEmployeePhone());
                _Employee.setEmployeeAddress(employee.getEmployeeAddress());
                _Employee.setEmployeeGender(employee.getEmployeeGender());
                _Employee.setEmployeePosition(employee.getEmployeePosition());
                _Employee.setEmployeeSalary(employee.getEmployeeSalary());
                employeeRepository.save(_Employee);
            } catch (Exception e) {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS, oneE);
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
        }
    }

    public Response deleteEmployee(String id) {
        Optional<Employee> oneEm = employeeRepository.findById(id);
        if (oneEm.isPresent()) {
            try {
                Employee _Employee = oneEm.get();
                employeeRepository.deleteById(id);
                accountRepository.deleteById(_Employee.getAccountId());
            } catch (Exception e) {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.DELETE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.DELETE_SUCCESS);
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
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

    public Response getAllEmployeeInfo() {
        List<EmployeeAccountHeadquarterTray> oneE;
        try {
            oneE = employeeAccountRepository.getAllInformation();
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.READ_FAIL);

        }
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, oneE);
    }
}
