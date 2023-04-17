package com.example.demo.config.middleware.auth.KIT;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.kit.jwt.JwtHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class AuthorizationHandler {
    private boolean verify;
    private HttpServletRequest request;
    private String header;
    private String jwtToken;
    private JwtHandler jwtHandlerOut;

    @Autowired
    private JwtHandler jwtHandler;

    public JwtHandler handleToken(HttpServletRequest request) {
        this.request = request;
        try {
            this.header = this.request.getHeader("Authorization");
            this.jwtToken = header.replace("Bearer ", "");
        } catch (Exception e) {
            System.out.println(e);
            this.jwtToken = null;
        }
        this.jwtHandlerOut = jwtHandler.verifyToken(this.jwtToken);
        return this.jwtHandlerOut;
    }
}
