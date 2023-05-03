package com.example.demo.config.middleware.auth;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.middleware.auth.kit.ResponseHandler;
import com.example.demo.entity.account.Account;
import com.example.demo.entity.account.AccountRepository;
import com.example.demo.entity.account.AccountService;
import com.example.demo.entity.employee.Employee;
import com.example.demo.kit.jwt.JwtFormat;
import com.example.demo.kit.jwt.JwtHandler;
import com.example.demo.kit.query.EmployeeAccountQuery;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountTray;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RefreshToken implements HandlerInterceptor {

    @Autowired
    private EmployeeAccountQuery employeeAccountQuery;

    @Autowired
    private JwtHandler jwtHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
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
        String employeeId = jsonMap.get("employeeId");
        String password = jsonMap.get("password");

        // Do something với email và password, ví dụ: kiểm tra, xử lý dữ liệu,...
        List<EmployeeAccountTray> account = employeeAccountQuery.findPasswordEmployee(employeeId);
        // Trả về true để cho phép request đi tiếp, hoặc false để chặn request
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        if (!account.isEmpty()) {
            EmployeeAccountTray oneAC = account.get(0);
            if (!bcrypt.matches(password, oneAC.getAccountPassword())) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Chuyển đối tượng phản hồi thành JSON
                ObjectMapper jsonBodyResponse = new ObjectMapper();
                String json = jsonBodyResponse
                        .writeValueAsString(new Response(HttpStatus.BAD_REQUEST, Message.LOGIN_FAIL));
                PrintWriter printWriter = response.getWriter();
                printWriter.write(json);
                printWriter.flush();
                return false;
            } else {
                final int MINUTE = 30;
                final int SECOND = 60;
                final int MILLISECOND = 1000;
                JwtFormat jwtFormat = new JwtFormat(employeeId, oneAC.getAccountRole());
                ObjectMapper convertJson = new ObjectMapper();
                String employeeAccountJson = convertJson.writeValueAsString(jwtFormat);
                String jwtToken = jwtHandler.generateToken(employeeAccountJson, MINUTE * SECOND * MILLISECOND);
                response.setHeader("Set-Cookie", "jwt-token=" + jwtToken + "; HttpOnly; Secure; SameSite=None");
                request.setAttribute("jwtToken", jwtToken);
                return true;
            }

        } else {
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
