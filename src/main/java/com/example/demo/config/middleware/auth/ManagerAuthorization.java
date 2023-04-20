package com.example.demo.config.middleware.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.middleware.auth.kit.AuthorizationHandler;
import com.example.demo.config.middleware.auth.kit.ResponseHandler;
import com.example.demo.config.middleware.auth.kit.Role;
import com.example.demo.kit.jwt.JwtResponse;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountTray;
import com.example.demo.kit.util.DiscordLogger;

@Component
public class ManagerAuthorization implements HandlerInterceptor {
    @Autowired
    private ResponseHandler responseHandler;
    @Autowired
    private AuthorizationHandler authorizationHandler;

    @Autowired
    private DiscordLogger discordLogger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        responseHandler.createResponse(request, response);
        JwtResponse jwtResponse = authorizationHandler.handleToken(request).authorizeLogin();
        if (jwtResponse.isVerify()) {
            EmployeeAccountTray _EAT = jwtResponse.getEmployeeAccountTray();
            String role = _EAT.getAccountRole();
            if (role.equals(Role.MANAGER)) {
                request.setAttribute("EmployeeId", _EAT.getEmployeeId());
                request.setAttribute("AccountEmail", _EAT.getAccountEmail());
                return true;
            } else {
                responseHandler.setContent(new Response(HttpStatus.BAD_REQUEST, Message.setInvalid("Role"))).send();
                return false;
            }

        } else {
            responseHandler.setContent(jwtResponse.getResponse()).send();
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // Xử lý sau khi controller đã xử lý request và trước khi response được gửi về`
        // client

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // Xử lý sau khi response đã được gửi về client

    }

}
