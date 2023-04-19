package com.example.demo.kit.tray;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class HeadquarterEmployeeGenderTray {
    // private String headquarterId;
    @Id
    private String headquarterName;
    private String employeeGenderMale;
    private String employeeGenderFemale;

}
