package com.example.demo.entity.employee;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.HeadquarterAccountTray;
import com.example.demo.kit.util.DiscordLogger;

@RestController
@RequestMapping("/api/v2/employee")
public class EmployeeController {
    @Autowired
    private DiscordLogger discordLogger;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public Response getAllEmployee(HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Get all employee.");
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}/show")
    public Response getEmployeeById(@PathVariable String id, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "View 1 someone's information.");
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/information")
    public Response getInfo(HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "View they's information.");
        return employeeService.getEmployeeInfo(employeeId);
    }

    @GetMapping("/all-information")
    public Response getAllInfo(HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "View all employee's information.");
        return employeeService.getAllEmployeeInfo();
    }

    @PostMapping("/store")
    public Response createEmployee(
            @RequestBody HeadquarterAccountTray headquarterAccount, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Store one employee.");
        return employeeService.storeEmployee(headquarterAccount);
    }

    @PostMapping("/multiple-store")
    public Response createEmployees(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        long startTime = System.nanoTime();

        Response result = employeeService.storeEmployeeFromExcel(file);

        long endTime = System.nanoTime();

        long durationInSeconds = (endTime - startTime) / 1000000000;
        discordLogger.no1Send(email,
                String.format("Has implemented multiple storing -> total cost %s sec.",
                        durationInSeconds));
        return result;
    }

    @PutMapping("/{id}/update")
    public Response updateEmployee(@PathVariable("id") String id,
            @RequestBody Employee employee, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Update one eployee.");
        return employeeService.updateEmployee(id, employee);
    }

    @PutMapping("/self-update")
    public Response updateEmployeSelf(HttpServletRequest request,
            @RequestBody Employee employee) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Update myself.");
        return employeeService.updateSelf(employeeId, employee);
    }

    @DeleteMapping("/{id}/delete")
    public Response deleteEmployee(@PathVariable("id") String id, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Delete one employee.");
        return employeeService.deleteEmployee(id);
    }

    @PostMapping("/create-avatar")
    public Response storeAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Set avatar.");
        return employeeService.storeImage(employeeId, file);
    }

    @GetMapping("/avatar/{employeeId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String employeeId, HttpServletRequest request) {
        // String employeeId = (String) request.getAttribute("EmployeeId");
        return employeeService.getAvatar(employeeId);
    }

}
