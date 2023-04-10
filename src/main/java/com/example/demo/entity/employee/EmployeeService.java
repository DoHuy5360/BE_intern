package com.example.demo.entity.employee;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.utilities.Time;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> one_Employee = employeeRepository.findById(id);
        return one_Employee.orElse(null);
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public ResponseEntity<Employee> updateEmployee(String id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
    
        if (!optionalEmployee.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Employee existingEmployee = optionalEmployee.get();
    
        existingEmployee.setHeadquarterId(employee.getHeadquarterId());
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setEmployeePhone(employee.getEmployeePhone());
        existingEmployee.setEmployeeAddress(employee.getEmployeeAddress());
        existingEmployee.setEmployeeGender(employee.getEmployeeGender());
        existingEmployee.setEmployeePosition(employee.getEmployeePosition());
        existingEmployee.setEmployeeSalary(employee.getEmployeeSalary());
        existingEmployee.setUpdateAt(Time.getCurrentDate());




        existingEmployee.setUpdateAt(Time.getCurrentDate());
    
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
    
        return ResponseEntity.ok(updatedEmployee);
    }

    public void deleteEmployeee(String id) {
        employeeRepository.deleteById(id);
    }

    public String uploadEmployeeImage(String path,String id, MultipartFile file) throws IOException {

        // construct file path using employee ID as file name
        String fileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = path+ File.separator+fileName;

        // create image folder if it doesn't exist
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
            folder.setExecutable(true, false);
            folder.setReadable(true, false);
            folder.setWritable(true, false);
        }

        // save image file to folder
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }
    public String updateEmployeeImage(String path, String id, MultipartFile file) throws IOException {
        // construct file path using employee ID as file name
        String fileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = path + File.separator + fileName;
    
        // save image file to folder
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
    
        return fileName;
    }

    public boolean deleteEmployeeImage(String path, String id) {
        // construct file path using employee ID as file name
        String fileName = id + ".png";
        String filePath = path + File.separator + fileName;
        
        // delete file if it exists
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }
    public byte[] getEmployeeImage(String path, String id) throws IOException {
        String fileName = id + ".png"; // assuming all employee images are PNG files
        String filePath = path + File.separator + fileName;
        File imageFile = new File(filePath);
        return Files.readAllBytes(imageFile.toPath());
    }
}
