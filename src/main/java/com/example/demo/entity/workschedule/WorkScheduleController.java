package com.example.demo.entity.workschedule;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1/workschedule")
@CrossOrigin("*")
public class WorkScheduleController {
    @Autowired
    private WorkScheduleService workScheduleService;

    @GetMapping("/")
    public List<WorkSchedule> index() {
        return workScheduleService.getRecord();
    }

    @GetMapping("/{id}")
    public WorkSchedule show(@PathVariable String id) {
        return workScheduleService.getOneRecord(id);
    }

    @PostMapping("/store")
    public ResponseEntity<WorkSchedule> store(@RequestBody WorkSchedule workSchedule) {
        return workScheduleService.storeRecord(workSchedule);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return workScheduleService.deleteRecord(id);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<WorkSchedule> update(@PathVariable String id, @RequestBody WorkSchedule workSchedule) {
        return workScheduleService.updateRecord(id, workSchedule);
    }
}
