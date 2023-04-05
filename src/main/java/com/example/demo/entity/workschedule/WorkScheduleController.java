package com.example.demo.entity.workschedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.KIT.RES.Response;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/workschedule")
@CrossOrigin("*")
public class WorkScheduleController {
    @Autowired
    private WorkScheduleService workScheduleService;

    @GetMapping("/")
    public Response index() {
        return workScheduleService.getRecord();
    }

    @GetMapping("/all-information")
    public Response getAllInformation() {
        return workScheduleService.getAllInfo();
    }

    @GetMapping("/{id}")
    public Response show(@PathVariable String id) {
        return workScheduleService.getOneRecord(id);
    }

    @PostMapping("/store")
    public Response store(@RequestBody WorkSchedule workSchedule) {
        return workScheduleService.storeRecord(workSchedule);
    }

    @DeleteMapping("/{id}/delete")
    public Response delete(@PathVariable String id) {
        return workScheduleService.deleteRecord(id);
    }

    @PutMapping("/{id}/update")
    public Response update(@PathVariable String id, @RequestBody WorkSchedule workSchedule) {
        return workScheduleService.updateRecord(id, workSchedule);
    }
}
