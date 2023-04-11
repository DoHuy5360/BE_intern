package com.example.demo.config.middleware.auth;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.account.Account;
import com.example.demo.entity.account.AccountService;
import com.example.demo.entity.employee.Employee;
import com.example.demo.kit.JWT.JwtHandler;
import com.example.demo.kit.RES.Message;
import com.example.demo.kit.RES.Response;
import com.example.demo.kit.TRAY.EmployeeAccountTray;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Authentication implements HandlerInterceptor {

    @Autowired
    AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // Cho phép tất cả các nguồn gốc
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE"); // Cho phép các phương thức POST,
                                                                                      // GET, PUT, DELETE
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Cho phép các tiêu đề
                                                                                           // Content-Type và
                                                                                           // Authorization
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Cho phép sử dụng thông tin đăng nhập của
                                                                        // người dùng
        // Lấy đối tượng BufferedReader để đọc dữ liệu từ luồng đầu vào của request
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;

        // Đọc từng dòng dữ liệu từ luồng đầu vào và nối vào StringBuilder
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        // Lấy chuỗi dữ liệu (body) của request
        String requestBody = body.toString();

        // Chuyển đổi dữ liệu JSON sang đối tượng Java sử dụng thư viện Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, String>>() {
        });

        // Lấy email và password từ đối tượng Map chứa dữ liệu JSON
        String email = jsonMap.get("email");
        String password = jsonMap.get("password");

        // Do something với email và password, ví dụ: kiểm tra, xử lý dữ liệu,...
        List<EmployeeAccountTray> account = accountService.checkLogin(email, password);
        // Trả về true để cho phép request đi tiếp, hoặc false để chặn request
        if (account.isEmpty()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Chuyển đối tượng phản hồi thành JSON
            ObjectMapper jsonBodyResponse = new ObjectMapper();
            String json = jsonBodyResponse.writeValueAsString(new Response(HttpStatus.BAD_REQUEST, Message.LOGIN_FAIL));
            PrintWriter printWriter = response.getWriter();
            printWriter.write(json);
            printWriter.flush();
            return false;
        } else {
            final int MINUTE = 15;
            final int SECOND = 60;
            final int MILLISECOND = 1000;
            ObjectMapper convertJson = new ObjectMapper();
            String employeeAccountJson = convertJson.writeValueAsString(account.get(0));
            String jwtToken = JwtHandler.generateToken(employeeAccountJson, MINUTE * SECOND * MILLISECOND);
            response.setHeader("Set-Cookie", "jwt-token=" + jwtToken + "; HttpOnly; Secure; SameSite=None");
            request.setAttribute("jwtToken", jwtToken);
            request.setAttribute("email", email);
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // Xử lý sau khi controller đã xử lý request và trước khi response được gửi về
        // client
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // Xử lý sau khi response đã được gửi về client
    }

}
