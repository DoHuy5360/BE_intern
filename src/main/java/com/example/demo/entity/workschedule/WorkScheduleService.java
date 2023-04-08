package com.example.demo.entity.workschedule;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.KIT.Query.EmployeeWorkscheduleQuery;
import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.EmployeeWorkscheduleTray;
import com.example.demo.utilities.Time;

@Service
public class WorkScheduleService {
    @Autowired
    private WorkScheduleRepository workScheduleRepository;
    @Autowired
    private EmployeeWorkscheduleQuery employeeWorkscheduleQuery;

    public Response getRecord() {
        List<WorkSchedule> workSchedules = (List<WorkSchedule>) workScheduleRepository.findAll();
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, workSchedules.size(), workSchedules);
    }

    public Response getAllMySchedule(String id) {
        List<WorkSchedule> oneWS = workScheduleRepository.findWorkScheduleById(id);
        if (oneWS.isEmpty()) {
            return new Response(HttpStatus.OK, Message.setEmptyMessage("Data"));

        } else {
            return new Response(HttpStatus.OK, Message.READ_SUCCESS, oneWS.size(), oneWS);
        }
    }

    public Response getAllInfo() {
        List<EmployeeWorkscheduleTray> manyWS;
        try {
            manyWS = employeeWorkscheduleQuery.getWorkSchedule();

        } catch (Exception e) {
            return new Response(HttpStatus.BAD_REQUEST, Message.READ_FAIL);

        }
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, manyWS);
    }

    public Response getOneRecord(String id) {
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(id);
        return oneWS.isPresent() ? new Response(HttpStatus.OK, Message.READ_SUCCESS, oneWS)
                : new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);

    }

    public Response storeRecord(String employeeId, WorkSchedule workSchedule) {
        workSchedule.setEmployeeId(employeeId);
        workScheduleRepository.save(workSchedule);
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(workSchedule.getWorkScheduleId());
        if (oneWS.isPresent()) {
            return new Response(HttpStatus.OK, Message.CREATE_SUCCESS, oneWS.get());
        } else {
            return new Response(HttpStatus.BAD_REQUEST, Message.CREATE_FAIL);
        }
    }

    @Transactional
    public Response deleteRecord(String employeeId, String id) {
        workScheduleRepository.deleteByEmployeeIdAndWorkScheduleId(employeeId, id);
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(id);

        return (oneWS.isEmpty()) ? new Response(HttpStatus.OK, Message.DELETE_SUCCESS)
                : new Response(HttpStatus.NOT_FOUND, Message.DELETE_FAIL);
    }

    @Transactional
    public Response updateRecord(String employeeId, String id, WorkSchedule workSchedule) {
        Optional<WorkSchedule> oneWS = workScheduleRepository.findById(id);
        if (oneWS.isPresent()) {
            WorkSchedule _WS = oneWS.get();
            if (_WS.getEmployeeId().equals(employeeId)) {
                try {
                    _WS.setEmployeeId(workSchedule.getEmployeeId());
                    _WS.setWorkSchedulePlan(workSchedule.getWorkSchedulePlan());
                    _WS.setWorkScheduleTime(workSchedule.getWorkScheduleTime());
                    _WS.setWorkSchedulePlace(workSchedule.getWorkSchedulePlace());
                    _WS.setWorkScheduleColor(workSchedule.getWorkScheduleColor());
                    _WS.setWorkScheduleTime(Time.getDeadCurrentDate());
                    workScheduleRepository.save(_WS);
                } catch (Exception e) {
                    return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
                }
                return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS);
            } else {
                return new Response(HttpStatus.BAD_REQUEST, Message.setNotMatch("User Id"));
            }
        } else {
            return new Response(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
        }

    }
}
