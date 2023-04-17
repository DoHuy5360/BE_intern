package com.example.demo.kit.tray;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeAccountTray {
    @Id
    private String employeeId;
    private String accountRole;
    private String accountPassword;
    private String accountEmail;
}
