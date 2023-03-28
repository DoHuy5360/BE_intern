package com.example.demo.entity.timekeeping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/timekeeping")
@CrossOrigin("*")
public class TimekeepingController {
    @Autowired
    private TimekeepingService timekeepingService;

    @GetMapping("/")
    public ResponseEntity<List<Timekeeping>> index() {
        List<Timekeeping> timekeeping_record = timekeepingService.getRecordTimekeeping();
        System.out.println(timekeeping_record);
        return new ResponseEntity<>(timekeeping_record, HttpStatus.OK);
    }

    @GetMapping("/{id}/show")
    public Timekeeping show(@PathVariable("id") String id) {
        Timekeeping timekeeping_one = timekeepingService.getOneRecordTimekeeping(id);
        return timekeeping_one;
    }

    @PostMapping("/store")
    public void store(@RequestBody Timekeeping timekeeping) {
        System.out.println(timekeeping);
        timekeepingService.storeTimekeeping(timekeeping);
    }

    @PutMapping("/{id}/update")
    public void update(@PathVariable("id") String id, @RequestBody Timekeeping timekeeping) {
        timekeepingService.updateTimekeeping(id, timekeeping);
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable String id) {
        timekeepingService.deleteTimekeeping(id);
    }
}
