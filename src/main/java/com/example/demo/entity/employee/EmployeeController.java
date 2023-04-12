package com.example.demo.entity.employee;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.HeadquarterAccountTray;
import com.example.demo.kit.util.DiscordLoger;

@RestController
@RequestMapping("/api/v2/employee")
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
    public Response createEmployees(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        long startTime = System.nanoTime();

        Response result = employeeService.storeEmployeeFromExcel(file);

        long endTime = System.nanoTime();

        long durationInSeconds = (endTime - startTime) / 1000000000; // Thời gian xử lý tính bằng second
        new DiscordLoger().prepareContent(
                String.format("```⏳ %s has implemented multiple storing -> total cost %s sec.```", email,
                        durationInSeconds))
                .send();
        return result;
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

    @PostMapping("/create-avatar")
    public Response storeAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        return employeeService.storeImage(employeeId, file);
    }

}
