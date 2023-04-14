package com.example.demo.kit.jwt;

import org.springframework.stereotype.Component;

import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountTray;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class JwtResponse {
    private boolean verify;
    private Response response;
    private EmployeeAccountTray employeeAccountTray;

    public JwtResponse createJwtResponse(boolean verify, EmployeeAccountTray employeeAccountTray, Response response) {
        this.verify = verify;
        this.response = response;
        this.employeeAccountTray = employeeAccountTray;
        return this;
    }

    public JwtResponse createJwtResponse(boolean verify, Response response) {
        this.verify = verify;
        this.response = response;
        this.employeeAccountTray = null;
        return this;
    }

}