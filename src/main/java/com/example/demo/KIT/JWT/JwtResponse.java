package com.example.demo.kit.jwt;

import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountTray;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private boolean verify;
    private EmployeeAccountTray employeeAccountTray;
    private Response response;

    public JwtResponse(boolean verify, EmployeeAccountTray employeeAccountTray, Response response) {
        this.verify = verify;
        this.employeeAccountTray = employeeAccountTray;
        this.response = response;
    }

    public JwtResponse(boolean verify, Response response) {
        this.verify = verify;
        this.response = response;
    }

}