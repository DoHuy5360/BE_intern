package com.example.demo.entity.workschedule;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kit.res.Response;
import com.example.demo.kit.util.DiscordLogger;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v2/workschedule")
public class WorkScheduleController {
    @Autowired
    private DiscordLogger discordLogger;

    @Autowired
    private WorkScheduleService workScheduleService;

    @GetMapping("/")
    public Response index() {
        return workScheduleService.getRecord();
    }

    @GetMapping("/self-schedule")
    public Response getAll(HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "View their own Work Schedule");
        return workScheduleService.getAllMySchedule(employeeId);
    }

    @GetMapping("/all-information")
    public Response getAllInformation(HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "View all Work Schedule");
        return workScheduleService.getAllInfo();
    }

    @GetMapping("/{id}/show")
    public Response show(@PathVariable String id, HttpServletRequest request) {
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "View one Work Schedule");
        return workScheduleService.getOneRecord(id);
    }

    @PostMapping("/store")
    public Response store(@RequestBody WorkSchedule workSchedule, HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Create one Work Schedule");
        return workScheduleService.storeRecord(employeeId, workSchedule);
    }

    @DeleteMapping("/{id}/delete")
    public Response delete(@PathVariable String id, HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Delete one Work Schedule");
        return workScheduleService.deleteRecord(employeeId, id);
    }

    @PutMapping("/{id}/update")
    public Response update(@PathVariable String id, HttpServletRequest request,
            @RequestBody WorkSchedule workSchedule) {
        String employeeId = (String) request.getAttribute("EmployeeId");
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no1Send(email, "Update one Work Schedule");
        return workScheduleService.updateRecord(employeeId, id, workSchedule);
    }
}
