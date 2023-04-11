package com.example.demo.entity.employee;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.HeadquarterAccountTray;

@RestController
@RequestMapping("/api/v2/employee")
// @CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public Response getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}/show")
    public Response getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/information")
    public Response getInfo(HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        return employeeService.getEmployeeInfo(employeeId);
    }

    @GetMapping("/all-information")
    public Response getAllInfo() {
        return employeeService.getAllEmployeeInfo();
    }

    @PostMapping("/store")
    public Response createEmployee(
            @RequestBody HeadquarterAccountTray headquarterAccount) {
        return employeeService.storeEmployee(headquarterAccount);
    }

    @PostMapping("/multiple-store")
    public Response createEmployees(@RequestParam("file") MultipartFile file) {
        return employeeService.storeEmployeeFromExcel(file);
    }

    @PutMapping("/{id}/update")
    public Response updateEmployee(@PathVariable("id") String id,
            @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PutMapping("/self-update")
    public Response updateEmployeSelf(HttpServletRequest request,
            @RequestBody Employee employee) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        return employeeService.updateSelf(employeeId, employee);
    }

    @DeleteMapping("/{id}/delete")
    public Response deleteEmployee(@PathVariable("id") String id) {
        return employeeService.deleteEmployee(id);
    }

}
