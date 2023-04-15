package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.util.DiscordLogger;

@RestController
public class testController {

    @Autowired
    private DiscordLogger discordLogger;

    @GetMapping("/")
    public String index() {
        return "Truy cập " + "<a href='https://github.com/DoHuy5360/BE_intern' target='_blank'>REST description</a>"
                + "để xem";
    }

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
        return "Token still alive";
    }
}
