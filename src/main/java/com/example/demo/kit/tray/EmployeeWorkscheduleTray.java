package com.example.demo.kit.tray;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeWorkscheduleTray {
    @Id
    private String workScheduleId;
    private String employeeId;
    private String employeeName;
    private String employeePhone;
    private String employeeAvatar;
    private String headquarterName;
    private String workSchedulePlan;
    private String employeePosition;
    private String workSchedulePlace;
    private String workScheduleColor;
    private String workScheduleTimeIn;
    private String headquarterAddress;
    private String workScheduleTimeOut;
    private String workScheduleDeparture;
    private String workScheduleDestination;

}
