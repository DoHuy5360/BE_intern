package com.example.demo.entity.account;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.dto.AccountEmployeeDTO;
import com.example.demo.entity.employee.Employee;
import com.example.demo.entity.employee.EmployeeService;
import com.example.demo.entity.employee.EmployeeRepository;

import com.example.demo.utilities.Time;
    
@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private EmployeeService employeeService;
    @Override
    public List<Account> getAllAccounts(){
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    @Override
    public List<AccountEmployeeDTO> getAllAccountsAndEmployees() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        List<AccountEmployeeDTO> accountEmployeeDTOList = new ArrayList<>();
        for (Account account : accounts) {
            Optional<Employee> optionalEmployee = employeeRepository.findByAccountId(account.getAccountId());
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                accountEmployeeDTOList.add(new AccountEmployeeDTO(account, employee));
            }
        }
        return accountEmployeeDTOList;
    }

    @Override
    public Account getAccountById(String id) {
        Optional<Account> one_account = accountRepository.findById(id);
        return one_account.orElse(null);
    }

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    
    @Override
    public ResponseEntity<Account> updateAccount(String id, Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
    
        if (!optionalAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Account existingAccount = optionalAccount.get();
    
        existingAccount.setAccountEmail(account.getAccountEmail());
        existingAccount.setAccountPassword(account.getAccountPassword());
        existingAccount.setAccountRole(account.getAccountRole());
        existingAccount.setUpdateAt(Time.getCurrentDate());
    
        Account updatedAccount = accountRepository.save(existingAccount);
    
        return ResponseEntity.ok(updatedAccount);
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
    @Override
    public void deleteAccountWithEmployee(String id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
    if (optionalAccount.isPresent()) {
        Account account = optionalAccount.get();
        String accountId = account.getAccountId();
        Optional<Employee> optionalEmployee = employeeRepository.findByAccountId(accountId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            String employeeId = employee.getEmployeeId();
            employeeRepository.deleteById(employeeId);
        }
        accountRepository.deleteById(id);
    } else {
        throw new EntityNotFoundException("Account not found with id: " + id);
    }

    }

    @Override
    public Account createAccountWithEmployee(AccountRequest request) {
        Account account = new Account();
        account.setAccountEmail(request.getAccountEmail());
        account.setAccountPassword(request.getAccountPassword());
        account.setAccountRole(request.getAccountRole());

        Employee employee = new Employee();
        employee.setHeadquarterId(request.getHeadquarterId());
        employee.setEmployeeName(request.getEmployeeName());
        employee.setEmployeePhone(request.getEmployeePhone());
        employee.setEmployeeAddress(request.getEmployeeAddress());
        employee.setEmployeeGender(request.getEmployeeGender());
        employee.setEmployeePosition(request.getEmployeePosition());
        employee.setEmployeeSalary(request.getEmployeeSalary());
        employee.setAccountId(account.getAccountId());

        accountRepository.save(account);

        // You will need to implement the createEmployee method in EmployeeService
        employeeService.createEmployee(employee);

        return account;
    }
    @Override
    public Account updateAccountWithEmployee(String id, AccountRequest request) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAccountEmail(request.getAccountEmail());
            account.setAccountPassword(request.getAccountPassword());
            account.setAccountRole(request.getAccountRole());
            account.setUpdateAt(Time.getCurrentDate());

            String accountId = account.getAccountId();
            Optional<Employee> optionalEmployee = employeeRepository.findByAccountId(accountId);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.setHeadquarterId(request.getHeadquarterId());
                employee.setEmployeeName(request.getEmployeeName());
                employee.setEmployeePhone(request.getEmployeePhone());
                employee.setEmployeeAddress(request.getEmployeeAddress());
                employee.setEmployeeGender(request.getEmployeeGender());
                employee.setEmployeePosition(request.getEmployeePosition());
                employee.setEmployeeSalary(request.getEmployeeSalary());
                employee.setUpdateAt(Time.getCurrentDate());
                employeeService.updateEmployee(employee.getEmployeeId(), employee);
            } else {
                Employee employee = new Employee();
                employee.setHeadquarterId(request.getHeadquarterId());
                employee.setEmployeeName(request.getEmployeeName());
                employee.setEmployeePhone(request.getEmployeePhone());
                employee.setEmployeeAddress(request.getEmployeeAddress());
                employee.setEmployeeGender(request.getEmployeeGender());
                employee.setEmployeePosition(request.getEmployeePosition());
                employee.setEmployeeSalary(request.getEmployeeSalary());
                employee.setAccountId(account.getAccountId());
                employee.setUpdateAt(Time.getCurrentDate());
                employeeService.createEmployee(employee);
            }

            accountRepository.save(account);
            return account;
        } else {
            throw new EntityNotFoundException("Account not found with id: " + id);
        }
    }
    
}