package com.example.demo.kit.tray;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeAccountHeadquarterTray {
    @Id
    private String employeeId;
    private String accountId;
    private String headquarterId;
    private String employeeName;
    private String employeePhone;
    private String employeeAddress;
    private String employeeGender;
    private String employeePosition;
    private String employeeAvatar;
    private int employeeSalary;
    private String accountEmail;
    private String accountRole;
    private String headquarterName;
    private String headquarterAddress;
}
