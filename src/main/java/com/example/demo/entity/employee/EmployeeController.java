package com.example.demo.entity.employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.KIT.DTO.EmployeeAccountHeadquarter;
import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.EmployeeAccountHeadquarterTray;
import com.example.demo.KIT.TRAY.HeadquarterAccountTray;
import com.example.demo.entity.account.Account;
import com.example.demo.entity.account.AccountService;
import com.example.demo.entity.headquarter.Headquarter;
import com.example.demo.entity.headquarter.HeadquarterService;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin("*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private HeadquarterService headquarterService;

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
        try {
            Account _account = new Account();
            _account.setAccountEmail(headquarterAccount.getAccountEmail());
            _account.setAccountPassword(headquarterAccount.getAccountPassword());
            _account.setAccountRole(headquarterAccount.getAccountRole());

            Employee _employee = new Employee();
            _employee.setAccountId(_account.getAccountId());
            _employee.setHeadquarterId(headquarterAccount.getHeadquarterId());
            _employee.setEmployeePosition(headquarterAccount.getEmployeePosition());
            _employee.setEmployeeAvatar(
                    "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg");

            accountService.storeAccount(_account);
            employeeService.storeEmployee(_employee);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL);
        }

        return new Response(HttpStatus.OK, Message.CREATE_SUCCESS, headquarterAccount);
    }

    @PostMapping("/store/multiple")
    @Transactional
    public Response createEmployees(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            // Sử dụng XSSFWorkbook nếu file có định dạng .xlsx, sử
            // dụng HSSFWorkbook nếu file có định dạng .xls
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên trong file
            Iterator<Row> rowIterator = sheet.iterator();
            int limitCount = 5;
            int cellCount = 0;
            Row row = rowIterator.next(); // todo: bypass first row ( title )
            Boolean emptyRow = false;
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                if (row == null) {
                    break;
                }
                Account _account = new Account();
                Employee _employee = new Employee();
                Iterator<Cell> cellIterator = row.cellIterator();
                Boolean nextCellExist = cellIterator.hasNext();

                while (nextCellExist) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.BLANK) {
                        emptyRow = true;
                        break;
                    }
                    switch (cellCount) {
                        case 0:
                            _account.setAccountEmail(cell.getStringCellValue());
                            break;
                        case 1:
                            _account.setAccountRole(cell.getStringCellValue());
                            break;
                        case 2:
                            _account.setAccountPassword(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case 3:
                            _employee.setHeadquarterId(cell.getStringCellValue());
                            break;
                        case 4:
                            _employee.setEmployeePosition(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    if (emptyRow == true) {
                        break;
                    }
                    cellCount++;
                    if (cellCount % limitCount == 0) {
                        row = sheet.getRow(row.getRowNum() + 1);
                        if (row == null) {
                            break;
                        }
                        cellIterator = row.cellIterator();
                        break;
                    }
                }

                _employee.setAccountId(_account.getAccountId());
                _employee.setEmployeeAvatar(
                        "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg");
                accountService.storeAccount(_account);
                employeeService.storeEmployee(_employee);

                cellCount = 0;
            }

            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            return new Response(HttpStatus.BAD_REQUEST, Message.UPLOAD_FAIL);
        }
        return new Response(HttpStatus.OK, Message.UPLOAD_SUCCESS);
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
