package com.example.demo.kit.excel;

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
    public int limitCell = 5;
    public AccountRepository accountRepository;
    public EmployeeRepository employeeRepository;
    public Sheet sheet;

    public ExcelAccountHandler(Sheet sheet, AccountRepository accountRepository,
            EmployeeRepository employeeRepository) {
        this.sheet = sheet;
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
    }

    public void store(int startRow, int endRow) {
        for (int i = startRow; i < endRow; i++) {
            Row row = this.sheet.getRow(i);
            Account _account = new Account();
            Employee _employee = new Employee();
            Cell cell0 = row.getCell(0);
            handleEmail(cell0, _account);
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

            // for (int j = 0; j < limitCell; j++) {
            // Cell cell = row.getCell(j);
            // System.out.println(startRow + "|" + cell.getNumericCellValue());
            // }
        }

    }

    public void handleEmail(Cell emailCell, Account _account) {
        try {
            String emailValue = emailCell.getStringCellValue();
            AccountValidation accountValidation = new AccountValidation(accountRepository)
                    .setEmail(emailValue)
                    .trackEmailFormat();
            if (accountRepository.getAccountByEmail(emailValue).isEmpty()
                    && accountValidation.isValid()) {
                _account.setAccountEmail(emailValue);
            } else {
                throw new Exception(Message.EMAIL_UNVALID);
            }
        } catch (Exception error) {
            // errors.add(error.getMessage());
        }
    }

    public void handleRole(Cell roleCell, Account _account) {
        try {
            _account.setAccountRole(roleCell.getStringCellValue());
        } catch (Exception error) {
            // errors.add(Message.ROLE_ERROR);
        }

    }

    public void handlePassword(Cell passwordCell, Account _account) {
        try {
            String password = passwordCell.getStringCellValue();
            _account.setAccountPassword(new BCryptPasswordEncoder().encode(password));
        } catch (Exception error) {
            // errors.add(Message.PASSWORD_ERROR);
        }

    }

    public void handleHeadquarterId(Cell headquarterIdCell, Employee _employee) {
        try {
            _employee.setHeadquarterId(headquarterIdCell.getStringCellValue());
        } catch (Exception error) {
            // errors.add(Message.HEADQUARTER_ID_ERROR);
        }

    }

    public void handleEmployeePosition(Cell employeePositionCell, Employee _employee) {
        try {
            _employee.setEmployeePosition(employeePositionCell.getStringCellValue());
        } catch (Exception error) {
            // errors.add(Message.POSITION_ERROR);
        }

    }

    // public void handle() {
    // if (emailCell.getCellType() != CellType.BLANK) {

    // if (errors.isEmpty()) {
    // try {
    // accountRepository.save(_account);
    // } catch (Exception e) {

    // emails.add(new EmployeeEmailExcelResponse(errors, emailCell.getRowIndex()));
    // break;
    // }
    // try {
    // employeeRepository.save(_employee);
    // } catch (Exception e) {

    // accountRepository.deleteById(_account.getAccountId());
    // }
    // } else {
    // emails.add(new EmployeeEmailExcelResponse(errors, emailCell.getRowIndex()));
    // }
    // } else {
    // // errors.add(new Exception(Message.EMAIL_ERROR).getMessage());
    // }
    // }
}
