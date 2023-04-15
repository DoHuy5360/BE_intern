package com.example.demo.entity.workschedule;

import java.util.List;
import java.util.Optional;

import org.jboss.jandex.PrimitiveType.Primitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.employee.EmployeeRepository;
import com.example.demo.kit.query.EmployeeWorkscheduleQuery;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeWorkscheduleTray;
import com.example.demo.kit.util.Time;
import com.example.demo.kit.validation.EmployeeValidation;
import com.example.demo.kit.validation.PrimitiveValidation;
import com.example.demo.kit.validation.WorkScheduleValidation;

@Service
public class WorkScheduleService {
    @Autowired
    private WorkScheduleRepository workScheduleRepository;
    @Autowired
    private EmployeeWorkscheduleQuery employeeWorkscheduleQuery;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Response getRecord() {
        List<WorkSchedule> workSchedules = (List<WorkSchedule>) workScheduleRepository.findAll();
        return new Response(HttpStatus.OK, Message.READ_SUCCESS, workSchedules.size(), workSchedules);
    }

    public Response getAllMySchedule(String employeeId) {
        try {
            EmployeeValidation employeeValidation = new EmployeeValidation(employeeRepository)
                    .setId(employeeId)
                    .trackEmployeeIdFormat()
                    .trackIdExist();
            if (employeeValidation.isValid()) {
                List<WorkSchedule> oneWS = workScheduleRepository.findWorkScheduleById(employeeId);
                return new Response(HttpStatus.OK, Message.READ_SUCCESS, oneWS.size(), oneWS);
            } else {
                return new Response(HttpStatus.OK, Message.INVALID, employeeValidation.getAmountErrors(),
                        employeeValidation.getErrors());
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.READ_FAIL);
        }

    }

    public Response getAllInfo() {
        try {
            List<EmployeeWorkscheduleTray> manyWS = employeeWorkscheduleQuery.getWorkSchedule();
            return new Response(HttpStatus.OK, Message.READ_SUCCESS, manyWS.size(), manyWS);
        } catch (Exception e) {
            System.out.println(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.READ_FAIL);

        }
    }

    public Response getOneRecord(String id) {
        try {
            WorkScheduleValidation workScheduleValidation = new WorkScheduleValidation(workScheduleRepository).setId(id)
                    .trackIdExist().trackWorkScheduleIdFormat();
            return (workScheduleValidation.isValid())
                    ? new Response(HttpStatus.OK, Message.READ_SUCCESS, workScheduleValidation.getEntityFound())
                    : new Response(HttpStatus.NOT_FOUND, Message.setInvalid("Work Schedule ID"),
                            workScheduleValidation.getAmountErrors(), workScheduleValidation.getErrors());

        } catch (Exception e) {
            System.out.println(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.READ_FAIL);
        }

    }

    public Response storeRecord(String employeeId, WorkSchedule workSchedule) {
        try {
            PrimitiveValidation _EWValidation;
            _EWValidation = new EmployeeValidation(employeeRepository)
                    .setId(employeeId)
                    .trackIdExist();
            _EWValidation = new WorkScheduleValidation(workSchedule)
                    .trackPlan().trackDateValid()
                    .trackDateInOut();
            if (_EWValidation.isValid()) {
                try {
                    workSchedule.setEmployeeId(employeeId);
                    WorkSchedule oneWs = workScheduleRepository.save(workSchedule);
                    return new Response(HttpStatus.OK, Message.CREATE_SUCCESS, 1, oneWs);
                } catch (Exception e) {
                    return new Response(HttpStatus.BAD_REQUEST, Message.CREATE_FAIL);
                }
            } else {
                return new Response(HttpStatus.BAD_REQUEST, Message.CREATE_FAIL,
                        _EWValidation.getAmountErrors(), _EWValidation.getErrors());

            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL);
        }
    }

    @Transactional
    public Response deleteRecord(String employeeId, String workScheduleId) {
        try {

            WorkScheduleValidation workScheduleValidation = new WorkScheduleValidation(workScheduleRepository)
                    .setId(workScheduleId)
                    .trackIdExist().trackWorkScheduleIdFormat();
            if (workScheduleValidation.isValid()) {
                try {
                    workScheduleRepository.deleteByEmployeeIdAndWorkScheduleId(employeeId, workScheduleId);
                    return new Response(HttpStatus.OK, Message.DELETE_SUCCESS);

                } catch (Exception e) {
                    System.out.println(e);
                    return new Response(HttpStatus.NOT_FOUND, Message.DELETE_FAIL);
                }
            } else {

                return new Response(HttpStatus.BAD_REQUEST, Message.INVALID, workScheduleValidation.getAmountErrors(),
                        workScheduleValidation.getErrors());
            }

        } catch (Exception e) {
            System.out.println(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.DELETE_FAIL);
        }
    }

    @Transactional
    public Response updateRecord(String employeeId, String workScheduleId, WorkSchedule workSchedule) {
        try {
            WorkScheduleValidation wsValidation = new WorkScheduleValidation(workScheduleRepository, workSchedule)
                    .trackPlan().trackDateValid()
                    .trackDateInOut().setId(workScheduleId).trackIdExist().trackBelongTo(employeeId);

            if (wsValidation.isValid()) {
                WorkSchedule _WS = (WorkSchedule) wsValidation.getEntityFound();
                try {
                    _WS.setWorkSchedulePlan(workSchedule.getWorkSchedulePlan());
                    _WS.setWorkScheduleTimeIn(workSchedule.getWorkScheduleTimeIn());
                    _WS.setWorkScheduleTimeOut(workSchedule.getWorkScheduleTimeOut());
                    _WS.setWorkSchedulePlace(workSchedule.getWorkSchedulePlace());
                    _WS.setWorkScheduleColor(workSchedule.getWorkScheduleColor());
                    _WS.setUpdateAt(Time.getCurrentTimeThangFormat());
                    workScheduleRepository.save(_WS);
                    return new Response(HttpStatus.OK, Message.UPDATE_SUCCESS, 1, _WS);
                } catch (Exception e) {
                    System.out.println(e);
                    return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
                }
            } else {

                return new Response(HttpStatus.BAD_REQUEST, Message.INVALID, wsValidation.getAmountErrors(),
                        wsValidation.getErrors());
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL);
        }

    }
}
