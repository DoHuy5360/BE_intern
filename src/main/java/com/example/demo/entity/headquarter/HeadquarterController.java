package com.example.demo.entity.headquarter;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.entity.timekeeping.Timekeeping;

@RestController
@RequestMapping("/api/headquarter")
public class HeadquarterController {
    @Autowired
    private HeadquarterService timekeepingService;

    @GetMapping("/")
    public ResponseEntity<List<Timekeeping>> getAllTimekeeping() {
        List<Timekeeping> timekeepingList = timekeepingService.getAllTimekeeping();
        return ResponseEntity.ok().body(timekeepingList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timekeeping> getTimekeepingById(@PathVariable Long id) {
        Timekeeping timekeeping = timekeepingService.getTimekeepingById(id);
        return ResponseEntity.ok().body(timekeeping);
    }

    @PostMapping("/")
    public ResponseEntity<Timekeeping> createTimekeeping(@RequestBody Timekeeping timekeeping) {
        Timekeeping newTimekeeping = timekeepingService.createTimekeeping(timekeeping);
        return ResponseEntity.ok().body(newTimekeeping);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timekeeping> updateTimekeeping(@PathVariable Long id, @RequestBody Timekeeping timekeeping) {
        Timekeeping updatedTimekeeping = timekeepingService.updateTimekeeping(id, timekeeping);
        return ResponseEntity.ok().body(updatedTimekeeping);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTimekeeping(@PathVariable Long id) {
        timekeepingService.deleteTimekeeping(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/accountRole/{accountRole}")
    public ResponseEntity<List<Timekeeping>> getTimekeepingByaccountRole(@PathVariable String accountRole) {
        List<Timekeeping> timekeepingList = timekeepingService.getTimekeepingByAccountRole(accountRole);
        return ResponseEntity.ok().body(timekeepingList);
    }

    @GetMapping("/accountId/{accountId}")
    public ResponseEntity<List<Timekeeping>> getTimekeepingByaccountId(@PathVariable String accountId) {
        List<Timekeeping> timekeepingList = timekeepingService.getTimekeepingByAccountId(accountId);
        return ResponseEntity.ok().body(timekeepingList);
    }

    @GetMapping("/time-range/{start}/{end}")
    public ResponseEntity<List<Timekeeping>> getTimekeepingByTimeRange(@PathVariable Date start, @PathVariable Date end) {
        List<Timekeeping> timekeepingList = timekeepingService.getTimekeepingByTimeRange(start, end);
        return ResponseEntity.ok().body(timekeepingList);
    }

    @GetMapping("/time-range/{start}/{end}/accountRole/{accountRole}")
    public ResponseEntity<List<Timekeeping>> getTimekeepingByTimeRangeAndaccountRole(@PathVariable Date start, @PathVariable Date end, @PathVariable String accountRole) {
        List<Timekeeping> timekeepingList = timekeepingService.getTimekeepingByTimeRangeAndAccountRole(start, end, accountRole);
        return ResponseEntity.ok().body(timekeepingList);
    }

    @GetMapping("/time-range/{start}/{end}/userid/{userId}")
    public ResponseEntity<List<Timekeeping>> getTimekeepingByTimeRangeAndUserId(@PathVariable Date start, @PathVariable Date end, @PathVariable String accountId) {
        List<Timekeeping> timekeepingList = timekeepingService.getTimekeepingByTimeRangeAndAccountId(start, end, accountId);
        return ResponseEntity.ok().body(timekeepingList);
    }

    @GetMapping("/time-range/{start}/{end}/accountRole/{accountRole}/userid/{userId}")
    public ResponseEntity<List<Timekeeping>> getTimekeepingByTimeRangeAndAccountRoleAndAccountId(@PathVariable Date start, @PathVariable Date end, @PathVariable String accountRole, @PathVariable String accountId) {
        List<Timekeeping> timekeepingList = timekeepingService.getTimekeepingByTimeRangeAndAccountRoleAndAccountId(start, end, accountRole, accountId);
        return ResponseEntity.ok().body(timekeepingList);
    }
}
