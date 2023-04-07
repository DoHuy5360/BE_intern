package com.example.demo.KIT.TRAY;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeAccountTray {
    @Id
    private String employeeId;
    private String accountRole;
}
