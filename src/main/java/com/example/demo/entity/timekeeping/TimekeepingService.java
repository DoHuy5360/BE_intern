package com.example.demo.entity.timekeeping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.utilities.Time;

@Service
public class TimekeepingService {
    @Autowired
    private TimekeepingRepository timekeepingRepository;

    public List<Timekeeping> getRecordTimekeeping() {
        return (List<Timekeeping>) timekeepingRepository.findAll();
    }

    public void storeTimekeeping(Timekeeping timekeeping) {
        timekeepingRepository.save(timekeeping);
    }

    public ResponseEntity<Timekeeping> updateTimekeeping(String id, Timekeeping timekeeping) {
        Optional<Timekeeping> one_timekeeping = timekeepingRepository.findById(id);
        if (one_timekeeping.isPresent()) {
            Timekeeping _Timekeeping = one_timekeeping.get();
            _Timekeeping.setHeadquarterId(timekeeping.getHeadquarterId());
            _Timekeeping.setEmployeeId(timekeeping.getEmployeeId());
            _Timekeeping.setTimekeepingType(timekeeping.getTimekeepingType());
            _Timekeeping.setUpdateAt(Time.getDeadCurrentDate());
            return new ResponseEntity<>(timekeepingRepository.save(_Timekeeping), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteTimekeeping(String id) {
        timekeepingRepository.deleteById(id);
    }

}
