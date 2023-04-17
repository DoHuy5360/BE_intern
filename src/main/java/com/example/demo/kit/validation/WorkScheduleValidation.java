package com.example.demo.kit.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.workschedule.WorkSchedule;
import com.example.demo.entity.workschedule.WorkScheduleRepository;
import com.example.demo.kit.res.Message;

public class WorkScheduleValidation extends PrimitiveValidation {
    public WorkSchedule workSchedule;
    public String workScheduleTimeIn;
    public String workScheduleTimeOut;
    public String workSchedulePlan;
    public WorkScheduleRepository workScheduleRepository;
    public WorkSchedule entity;

    public WorkScheduleValidation(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }

    public WorkScheduleValidation(WorkScheduleRepository workScheduleRepository, WorkSchedule workSchedule) {
        this.workScheduleRepository = workScheduleRepository;
        this.workSchedule = workSchedule;
        this.workScheduleTimeIn = workSchedule.getWorkScheduleTimeIn();
        this.workScheduleTimeOut = workSchedule.getWorkScheduleTimeOut();
        this.workSchedulePlan = workSchedule.getWorkSchedulePlan();

    }

    public WorkScheduleValidation(WorkSchedule workSchedule) {
        this.workSchedule = workSchedule;
        this.workScheduleTimeIn = workSchedule.getWorkScheduleTimeIn();
        this.workScheduleTimeOut = workSchedule.getWorkScheduleTimeOut();
        this.workSchedulePlan = workSchedule.getWorkSchedulePlan();
    }

    public WorkScheduleValidation setId(String id) {
        this.entityId = id;
        return this;
    }

    public WorkScheduleValidation trackWorkScheduleIdFormat() {
        if (!isIdValid("WS")) {
            this.errors.add(Message.setInvalid("Work Schedule ID"));
        }
        return this;
    }

    public WorkScheduleValidation trackBelongTo(String ownerId) {
        if (workScheduleEntityFound.isPresent()) {
            if (!this.workScheduleEntityFound.get().getEmployeeId().equals(ownerId)) {
                this.errors.add(Message.setNotMatch("Owner ID"));
            }
        }
        return this;
    }

    public WorkScheduleValidation trackIdExist() {
        this.workScheduleEntityFound = workScheduleRepository.findById(entityId);
        if (!workScheduleEntityFound.isPresent()) {
            this.errors.add(Message.setNotExistMessage("Work Schedule ID"));
        }
        return this;
    }

    public WorkScheduleValidation trackDateInOut() {
        if (this.workScheduleTimeIn.equals(this.workScheduleTimeOut)) {
            this.errors.add(Message.setInvalid("Time In & Time Out"));
        }
        return this;
    }

    public WorkScheduleValidation trackDateValid() {
        if (!isDateValid(this.workScheduleTimeIn)) {
            this.errors.add(Message.setInvalid("Time In"));
        }
        if (!isDateValid(this.workScheduleTimeOut)) {
            this.errors.add(Message.setInvalid("Time Out"));
        }
        return this;
    }

    public WorkScheduleValidation trackPlan() {
        if (isBlank(this.workSchedulePlan)) {
            this.errors.add(Message.setInvalid("Plan"));
        }
        return this;
    }
}
