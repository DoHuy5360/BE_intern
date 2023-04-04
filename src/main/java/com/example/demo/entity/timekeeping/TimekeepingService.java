package com.example.demo.entity.timekeeping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;
import com.example.demo.utilities.Time;

@Service
public class TimekeepingService {
    @Autowired
    private TimekeepingRepository timekeepingRepository;

    public Response getRecordTimekeeping() {
        List<Timekeeping> timekeepings = (List<Timekeeping>) timekeepingRepository.findAll();
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, timekeepings.size(), timekeepings);
    }

    public Response getOneRecordTimekeeping(String id) {
        Optional<Timekeeping> one_timekeeping = timekeepingRepository.findById(id);
        return (one_timekeeping.isPresent()) ? new Response(HttpStatus.OK, Message.READ_SUCCESS, one_timekeeping)
                : new Response(HttpStatus.OK, Message.READ_SUCCESS, one_timekeeping);
    }

    public void storeTimekeeping(Timekeeping timekeeping) {
        timekeepingRepository.save(timekeeping);
    }

    public Response updateTimekeeping(String id, Timekeeping timekeeping) {
        Optional<Timekeeping> one_timekeeping = timekeepingRepository.findById(id);
        if (one_timekeeping.isPresent()) {
            try {
                Timekeeping _Timekeeping = one_timekeeping.get();
                _Timekeeping.setHeadquarterId(timekeeping.getHeadquarterId());
                _Timekeeping.setEmployeeId(timekeeping.getEmployeeId());
                _Timekeeping.setTimekeepingType(timekeeping.getTimekeepingType());
                _Timekeeping.setUpdateAt(Time.getDeadCurrentDate());
                timekeepingRepository.save(_Timekeeping);

            } catch (Exception e) {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS, one_timekeeping);
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
        }
    }

    public void deleteTimekeeping(String id) {
        timekeepingRepository.deleteById(id);
    }

}
