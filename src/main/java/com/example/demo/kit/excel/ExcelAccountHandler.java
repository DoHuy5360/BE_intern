package com.example.demo.kit.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.Multipart;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.account.Account;
import com.example.demo.entity.account.AccountRepository;
import com.example.demo.entity.employee.Employee;
import com.example.demo.entity.employee.EmployeeRepository;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.validation.AccountValidation;

public class ExcelAccountHandler {
    public List<Account> dbAccounts;
    public List<String> dbEmails;
    public ArrayList<String> errors = new ArrayList<String>();
    public int limitCell = 5;
    public AccountRepository accountRepository;
    public EmployeeRepository employeeRepository;
    public Sheet sheet;

    public ExcelAccountHandler(
            Sheet sheet,
            AccountRepository accountRepository,
            EmployeeRepository employeeRepository) {
        this.dbAccounts = (List<Account>) accountRepository.findAll();
        this.dbEmails = dbAccounts.stream().map(Account::getAccountEmail).collect(Collectors.toList());
        this.sheet = sheet;
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
    }

    public void store(int startRow, int endRow) {
        for (int i = startRow; i < endRow; i++) {
            String email = "";
            try {
                Row row = this.sheet.getRow(i);
                Account _account = new Account();
                Employee _employee = new Employee();
                Cell emailCell = row.getCell(0);
                handleEmail(emailCell, _account);
                Cell cell1 = row.getCell(1);
                handleRole(cell1, _account);
                Cell cell2 = row.getCell(2);
                handlePassword(cell2, _account);
                Cell cell3 = row.getCell(3);
                handleHeadquarterId(cell3, _employee);
                Cell cell4 = row.getCell(4);
                handleEmployeePosition(cell4, _employee);
                _employee.setAccountId(_account.getAccountId());

                this.accountRepository.save(_account);
                this.employeeRepository.save(_employee);
                email = emailCell.getStringCellValue();
                this.dbEmails.add(email);
            } catch (Exception e) {
                System.out.println(e);
                this.dbEmails.remove(email);
                this.errors.add(Message.setInvalid("Row " + i));
            }
        }

    }

    public void handleEmail(Cell emailCell, Account _account) {
        try {
            String emailValue = emailCell.getStringCellValue();
            AccountValidation accountValidation = new AccountValidation(accountRepository)
                    .setEmail(emailValue)
                    .trackEmailFormat();
            if (!this.dbEmails.contains(emailValue)
                    && accountValidation.isValid()) {
                _account.setAccountEmail(emailValue);
            } else {
                throw new Exception(Message.EMAIL_UNVALID);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void handleRole(Cell roleCell, Account _account) {
        try {
            _account.setAccountRole(roleCell.getStringCellValue());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void handlePassword(Cell passwordCell, Account _account) {
        try {
            String password = passwordCell.getStringCellValue();
            _account.setAccountPassword(new BCryptPasswordEncoder().encode(password));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void handleHeadquarterId(Cell headquarterIdCell, Employee _employee) {
        try {
            _employee.setHeadquarterId(headquarterIdCell.getStringCellValue());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void handleEmployeePosition(Cell employeePositionCell, Employee _employee) {
        try {
            _employee.setEmployeePosition(employeePositionCell.getStringCellValue());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
