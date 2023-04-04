package com.example.demo.entity.employee;

import java.util.List;
import java.util.Optional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.KIT.Query.EmployeeAccountHeadquarterQuery;
import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.EmployeeAccountHeadquarterTray;
import com.example.demo.KIT.TRAY.HeadquarterAccountTray;
import com.example.demo.entity.account.Account;
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
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, employees.size(), employees);
    }

    public Response getEmployeeById(String EmployeeUserId) {
        Optional<Employee> one_E = employeeRepository.findById(EmployeeUserId);
        return (one_E.isPresent()) ? new Response(HttpStatus.OK, Message.READ_SUCCESS, one_E.get())
                : new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
    }

    public Response storeEmployee(HeadquarterAccountTray headquarterAccount) {
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

            accountRepository.save(_account);
            employeeRepository.save(_employee);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL);
        }
        return new Response(HttpStatus.OK, Message.CREATE_SUCCESS, headquarterAccount);
    }

    @Transactional
    public Response storeEmployeeFromExcel(MultipartFile file) {
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
                accountRepository.save(_account);
                employeeRepository.save(_employee);

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

    @Transactional
    public Response updateSelf(String employeeId, Employee employee) {
        Optional<Employee> oneE = employeeRepository.findById(employeeId);
        if (oneE.isPresent()) {
            try {
                Employee _Employee = oneE.get();
                // Name
                // Phone
                // Adress
                // Gender
                // Avatar
                _Employee.setEmployeeName(employee.getEmployeeName());
                _Employee.setEmployeePhone(employee.getEmployeePhone());
                _Employee.setEmployeeAddress(employee.getEmployeeAddress());
                _Employee.setEmployeeGender(employee.getEmployeeGender());
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
