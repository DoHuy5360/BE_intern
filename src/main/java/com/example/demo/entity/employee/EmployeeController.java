package com.example.demo.entity.employee;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // @Autowired
    // private EmployeeRepository employeeRepository;
    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/{id}/show")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") String id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }


    @PostMapping("/store")
    public  ResponseEntity<?> store(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/update")
    public void updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        System.out.println(employee);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") String id) {
        employeeService.deleteEmployeee(id);
        return ResponseEntity.noContent().build();
    }

    @Value("${project.image}")
    private String path; 



    @PostMapping("/{id}/uploadimage")
    public ResponseEntity<String> uploadEmployeeImage(@PathVariable("id") String id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = employeeService.uploadEmployeeImage(path, id, file);
            String fileUrl = "https://be-intern.onrender.com/images/" + fileName; // replace with your domain name
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload employee image");
        }
    }
    @PutMapping("/{id}/updateimage")
    public ResponseEntity<String> updateEmployeeImage(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = employeeService.updateEmployeeImage("C:\\Users\\nguye\\BE_intern\\images", id, file);
            String fileUrl = "https://be-intern.onrender.com/images/" + fileName; // modify this URL with your actual domain and image folder path
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update employee image");
        }
    }
    @DeleteMapping("/{id}/deleteimage")
    public ResponseEntity<String> deleteEmployeeImage(@PathVariable String id) {
        boolean deleted = employeeService.deleteEmployeeImage("C:\\Users\\nguye\\BE_intern\\images", id);
        if (deleted) {
            return ResponseEntity.ok("Employee image deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee image");
        }
    }
    @GetMapping("/{id}/showimage")
    public ResponseEntity<byte[]> getEmployeeImage(@PathVariable String id) {
        try {
            byte[] imageBytes = employeeService.getEmployeeImage("C:\\Users\\nguye\\BE_intern\\images", id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // assuming all employee images are PNG files
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
