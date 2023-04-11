package com.example.demo.entity.employee;

import java.util.List;
import java.util.Optional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.account.Account;
import com.example.demo.entity.account.AccountRepository;
import com.example.demo.entity.headquarter.Headquarter;
import com.example.demo.entity.headquarter.HeadquarterRepository;
import com.example.demo.kit.Interface.Validation;
import com.example.demo.kit.file.FileHandler;
import com.example.demo.kit.query.EmployeeAccountHeadquarterQuery;
import com.example.demo.kit.res.EmployeeEmailExcelResponse;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountHeadquarterTray;
import com.example.demo.kit.tray.HeadquarterAccountTray;
import com.example.demo.kit.validation.EmailValidation;
import com.example.demo.kit.validation.HeadquarterAccountValidation;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private HeadquarterRepository headquarterRepository;
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
        Validation headquarterAccountValidation = new HeadquarterAccountValidation(headquarterAccount,
                headquarterRepository)
                .trackEmail()
                .trackPassword()
                .trackHeadquarterId()
                .trackRole();
        if (headquarterAccountValidation.isValid()) {
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
            return new Response(HttpStatus.OK, Message.CREATE_SUCCESS);
        } else {
            return new Response(HttpStatus.BAD_REQUEST, Message.CREATE_FAIL,
                    headquarterAccountValidation.getAmountErrors(), headquarterAccountValidation.getErrors());
        }
    }

    public Response storeEmployeeFromExcel(MultipartFile multipartFile) {
        try {
            if (!multipartFile.isEmpty()) {
                ArrayList<EmployeeEmailExcelResponse> emails = new ArrayList<EmployeeEmailExcelResponse>();
                InputStream inputStream = multipartFile.getInputStream();
                Workbook workbook = new XSSFWorkbook(inputStream);
                // Sử dụng XSSFWorkbook nếu file có định dạng .xlsx, sử
                // dụng HSSFWorkbook nếu file có định dạng .xls
                Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên trong file
                Iterator<Row> rowIterator = sheet.iterator();
                Row row = rowIterator.next(); // todo: bypass first row ( title )
                while (rowIterator.hasNext()) {
                    row = rowIterator.next();
                    ArrayList<String> errors = new ArrayList<String>();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    if (cellIterator.hasNext()) {
                        Cell emailCell = cellIterator.next();
                        if (emailCell.getCellType() != CellType.BLANK) {
                            Account _account = new Account();
                            Employee _employee = new Employee();
                            try {
                                String emailValue = emailCell.getStringCellValue();
                                if (accountRepository.getAccountByEmail(emailValue).isEmpty()
                                        && EmailValidation.track(emailValue)) {
                                    _account.setAccountEmail(emailValue);
                                } else {
                                    throw new Exception(Message.EMAIL_UNVALID);
                                }
                            } catch (Exception error) {
                                errors.add(error.getMessage());
                            }
                            try {
                                _account.setAccountRole(cellIterator.next().getStringCellValue());
                                // throw new Exception(Message.ROLE_ERROR);
                            } catch (Exception error) {
                                // errors.add(error.getMessage());
                                errors.add(Message.ROLE_ERROR);
                            }
                            try {
                                _account.setAccountPassword(cellIterator.next().getStringCellValue());
                                // throw new Exception(Message.PASSWORD_ERROR);
                            } catch (Exception error) {
                                // errors.add(error.getMessage());
                                errors.add(Message.PASSWORD_ERROR);
                            }
                            try {
                                _employee.setHeadquarterId(cellIterator.next().getStringCellValue());
                                // throw new Exception(Message.HEADQUARTER_ID_ERROR);
                            } catch (Exception error) {
                                // errors.add(error.getMessage());
                                errors.add(Message.HEADQUARTER_ID_ERROR);
                            }
                            try {
                                _employee.setEmployeePosition(cellIterator.next().getStringCellValue());
                                // throw new Exception(Message.POSITION_ERROR);
                            } catch (Exception error) {
                                // errors.add(error.getMessage());
                                errors.add(Message.POSITION_ERROR);
                            }
                            _employee.setAccountId(_account.getAccountId());
                            _employee.setEmployeeAvatar(
                                    "https://charmouthtennisclub.org/wp-content/uploads/2021/01/placeholder-400x400.jpg");
                            if (errors.isEmpty()) {
                                try {
                                    accountRepository.save(_account);
                                } catch (Exception e) {
                                    emails.add(new EmployeeEmailExcelResponse(errors, emailCell.getRowIndex()));
                                    break;
                                }
                                try {
                                    employeeRepository.save(_employee);
                                } catch (Exception e) {
                                    accountRepository.deleteById(_account.getAccountId());
                                }
                            } else {
                                emails.add(new EmployeeEmailExcelResponse(errors, emailCell.getRowIndex()));
                            }
                        } else {
                            errors.add(new Exception(Message.EMAIL_ERROR).getMessage());
                        }

                    }
                }

                workbook.close();
                inputStream.close();

                return new Response(HttpStatus.OK, Message.CREATE_FAIL_AMOUNT, emails.size(), emails);
            } else {
                return new Response(HttpStatus.BAD_REQUEST, Message.setNotExistMessage("File"));
            }
        } catch (IOException e) {
            return new Response(HttpStatus.BAD_REQUEST, Message.setUploadFail("File"));
        }
    }

    @Transactional
    public Response updateEmployee(String employeeId, Employee employee) {
        Validation employeeValidation = new EmployeeValidation(employeeId, employee, employeeRepository,
                headquarterRepository)
                .trackIdExist()
                .trackHeadquarterId()
                .trackPhoneLength()
                .trackGenderLength();
        if (employeeValidation.isValid()) {
            Employee _Employee = employeeValidation.get();
            _Employee.setHeadquarterId(employee.getHeadquarterId());
            _Employee.setEmployeeName(employee.getEmployeeName());
            _Employee.setEmployeePhone(employee.getEmployeePhone());
            _Employee.setEmployeeAddress(employee.getEmployeeAddress());
            _Employee.setEmployeeGender(employee.getEmployeeGender());
            _Employee.setEmployeePosition(employee.getEmployeePosition());
            _Employee.setEmployeeSalary(employee.getEmployeeSalary());
            try {
                employeeRepository.save(_Employee);
            } catch (Exception e) {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS, _Employee);
        } else {
            return new Response(HttpStatus.BAD_REQUEST, Message.UPDATE_FAIL, employeeValidation.getAmountErrors(),
                    employeeValidation.getErrors());
        }
    }

    @Transactional
    public Response updateSelf(String employeeId, Employee employee) {
        Optional<Employee> oneE = employeeRepository.findById(employeeId);
        if (oneE.isPresent()) {
            try {
                Employee _Employee = oneE.get();
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

    public Response storeImage(String employeeId, MultipartFile file) {
        try {
            return (file.isEmpty()) ? new Response(HttpStatus.BAD_REQUEST, Message.setEmptyMessage("File"))
                    : new FileHandler(file).setPath("/image/avatar/").setName(employeeId).save();
        } catch (Exception e) {
            return new Response(HttpStatus.BAD_REQUEST, Message.setUploadFail("File"));
        }
    }
}
