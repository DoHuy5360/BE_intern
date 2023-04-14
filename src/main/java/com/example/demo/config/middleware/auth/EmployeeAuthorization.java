package com.example.demo.config.middleware.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.middleware.auth.KIT.AuthorizationHandler;
import com.example.demo.config.middleware.auth.KIT.ResponseHandler;
import com.example.demo.config.middleware.auth.KIT.Role;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;

@Component
public class EmployeeAuthorization implements HandlerInterceptor {
    @Autowired
    private ResponseHandler responseHandler;

    @Autowired
    private AuthorizationHandler authorizationHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        authorizationHandler.handleToken(request);
        responseHandler.createResponse(request, response);

        if (authorizationHandler.isVerify()) {
            String role = authorizationHandler.getAccountRole();
            if (role.equals(Role.EMPLOYEE) || role.equals(Role.MANAGER)) {
                request.setAttribute("EmployeeId", authorizationHandler.getEmployeeId());
                request.setAttribute("AccountEmail", authorizationHandler.getAccountEmail());
                return true;
            } else {
                responseHandler.setContent(new Response(HttpStatus.BAD_REQUEST, Message.setInvalid("Role"))).send();
                return false;
            }

        } else {
            responseHandler.setContent(authorizationHandler.getResponse()).send();
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
