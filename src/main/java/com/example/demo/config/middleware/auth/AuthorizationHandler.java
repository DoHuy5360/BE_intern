package com.example.demo.config.middleware.auth;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.KIT.JWT.JwtHandler;
import com.example.demo.KIT.JWT.JwtResponse;
import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.TRAY.EmployeeAccountTray;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationHandler {
    private boolean verify;
    private HttpServletRequest request;
    private String header;
    private String jwtToken;
    private JwtResponse jwtResponse;

    public AuthorizationHandler(HttpServletRequest request) {
        this.request = request;
        this.header = this.request.getHeader("Authorization");
        this.jwtToken = header.replace("Bearer ", "");
        this.jwtResponse = JwtHandler.verifyToken(this.jwtToken);
        this.verify = this.jwtResponse.isVerify();
    }

    public String getAccountRole() {
        return this.jwtResponse.getEmployeeAccountTray().getAccountRole();
    }

    public String getEmployeeId() {
        return this.jwtResponse.getEmployeeAccountTray().getEmployeeId();
    }

    public Response getResponse() {
        return this.jwtResponse.getResponse();
    }
}
