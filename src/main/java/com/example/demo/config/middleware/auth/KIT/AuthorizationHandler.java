package com.example.demo.config.middleware.auth.KIT;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.kit.jwt.JwtHandler;
import com.example.demo.kit.jwt.JwtResponse;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountTray;

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
        try {
            this.header = this.request.getHeader("Authorization");
            this.jwtToken = header.replace("Bearer ", "");
        } catch (Exception e) {
            this.verify = false;
        }
        this.jwtResponse = JwtHandler.verifyToken(this.jwtToken);
        this.verify = this.jwtResponse.isVerify();
    }

    public String getAccountRole() {
        return this.jwtResponse.getEmployeeAccountTray().getAccountRole();
    }

    public String getEmployeeId() {
        return this.jwtResponse.getEmployeeAccountTray().getEmployeeId();
    }

    public String getAccountEmail() {
        return this.jwtResponse.getEmployeeAccountTray().getAccountEmail();
    }

    public Response getResponse() {
        return this.jwtResponse.getResponse();
    }
}
