package com.example.demo.kit.tray;

import com.example.demo.entity.headquarter.Headquarter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class HeadquarterAccountTray {
    private String accountEmail;
    private String accountPassword;
    private String accountRole;
    private String headquarterId;
    private String employeePosition;
}
