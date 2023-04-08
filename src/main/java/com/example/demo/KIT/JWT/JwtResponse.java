package com.example.demo.KIT.JWT;

import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.EmployeeAccountTray;

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