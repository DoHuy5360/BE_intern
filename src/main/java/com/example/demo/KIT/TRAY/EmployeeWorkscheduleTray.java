package com.example.demo.KIT.TRAY;

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
    private String employeePosition;
    private String workSchedulePlan;
    private String workScheduleTime;
    private String workScheduleColor;
    private String workSchedulePlace;
    private String headquarterName;
    private String headquarterAddress;

}
