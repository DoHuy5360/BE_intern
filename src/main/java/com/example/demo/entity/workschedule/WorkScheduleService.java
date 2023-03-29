package com.example.demo.entity.workschedule;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkScheduleService {
    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    public List<WorkSchedule> getRecord() {
        return (List<WorkSchedule>) workScheduleRepository.findAll();
    }

    public WorkSchedule getOneRecord(String id) {
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(id);
        return oneWS.orElse(null);
    }

    public ResponseEntity<WorkSchedule> storeRecord(WorkSchedule workSchedule) {
        workScheduleRepository.save(workSchedule);
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(workSchedule.getWorkScheduleId());
        if (oneWS.isPresent()) {
            return new ResponseEntity<>(oneWS.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteRecord(String id) {
        if (workScheduleRepository.existsById(id)) {
            workScheduleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<WorkSchedule> updateRecord(String id, WorkSchedule workSchedule) {
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(id);
        if (oneWS.isPresent()) {
            WorkSchedule _WS = oneWS.get();
            _WS.setWorkSchedulePlan(workSchedule.getWorkSchedulePlan());
            _WS.setWorkScheduleTime(workSchedule.getWorkScheduleTime());
            return new ResponseEntity<>(workScheduleRepository.save(_WS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
