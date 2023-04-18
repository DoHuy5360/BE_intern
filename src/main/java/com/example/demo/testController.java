package com.example.demo;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.employee.Employee;
import com.example.demo.entity.employee.EmployeeRepository;
import com.example.demo.kit.dotenv.DotenvHandler;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.util.DiscordLogger;

@RestController
public class testController {

    @Autowired
    private DiscordLogger discordLogger;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/login")
    public Response home(HttpServletRequest request) {
        String userIp = request.getRemoteAddr();
        int userPort = request.getRemotePort();
        Object jwtToken = (Object) request.getAttribute("jwtToken");
        String email = (String) request.getAttribute("AccountEmail");
        discordLogger.no0Send(email, "Has Logged in!");
        return new Response(HttpStatus.OK, Message.LOGIN_SUCCESS, jwtToken);

    }

    @PostMapping("/decode")
    public String decode() {
        String SECRET_KEY;
        try {
            SECRET_KEY = DotenvHandler.get("JWT_SECRET_KEY");
        } catch (Exception e) {
            return e.toString();

        }
        return SECRET_KEY;
    }
}
