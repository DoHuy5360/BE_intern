package com.example.demo.entity.workschedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.KIT.Query.EmployeeWorkscheduleQuery;
import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.EmployeeWorkscheduleTray;

@Service
public class WorkScheduleService {
    @Autowired
    private WorkScheduleRepository workScheduleRepository;
    @Autowired
    private EmployeeWorkscheduleQuery employeeWorkscheduleQuery;

    public List<WorkSchedule> getRecord() {
        return (List<WorkSchedule>) workScheduleRepository.findAll();
    }

    public Response getAllInfo() {
        List<EmployeeWorkscheduleTray> manyWS;
        try {
            manyWS = employeeWorkscheduleQuery.getWorkSchedule();
            System.out.println(manyWS);

        } catch (Exception e) {
            return new Response(HttpStatus.BAD_REQUEST, Message.READ_FAIL);

        }
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, manyWS);
    }

    public WorkSchedule getOneRecord(String id) {
        // Optional<WorkSchedule> oneWS =
        // workScheduleRepository.findById(id).orElseThrow();
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(id);
        return oneWS.orElse(null);
    }

    public Response storeRecord(WorkSchedule workSchedule) {
        workScheduleRepository.save(workSchedule);
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(workSchedule.getWorkScheduleId());
        if (oneWS.isPresent()) {
            return new Response(HttpStatus.OK, Message.CREATE_SUCCESS, oneWS.get());
        } else {
            return new Response(HttpStatus.BAD_REQUEST, Message.CREATE_FAIL);
        }
    }

    public Response deleteRecord(String id) {
        if (workScheduleRepository.existsById(id)) {
            workScheduleRepository.deleteById(id);
            return new Response(HttpStatus.OK, Message.DELETE_SUCCESS);
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.DELETE_FAIL);
        }
    }

    @Transactional
    public Response updateRecord(String id, WorkSchedule workSchedule) {
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(id);
        if (oneWS.isPresent()) {
            try {
                WorkSchedule _WS = oneWS.get();
                _WS.setWorkSchedulePlan(workSchedule.getWorkSchedulePlan());
                _WS.setWorkScheduleTime(workSchedule.getWorkScheduleTime());
                workScheduleRepository.save(_WS);
            } catch (Exception e) {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL);
            }
            return new Response(HttpStatus.OK, Message.CREATE_SUCCESS);
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
        }

    }
}
