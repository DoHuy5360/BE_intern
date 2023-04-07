package com.example.demo.config.middleware.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.KIT.JWT.JwtHandler;
import com.example.demo.KIT.TRAY.EmployeeAccountTray;

@Component
public class AdminAuthorization implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String header = request.getHeader("Authorization");
        String jwtToken = header.replace("Bearer ", "");

        EmployeeAccountTray re = JwtHandler.verifyToken(jwtToken);

        return (re.getAccountRole().equals("Admin")) ? true : false;
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
