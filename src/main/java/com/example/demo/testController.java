package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kit.RES.Message;
import com.example.demo.kit.RES.Response;
import com.example.demo.kit.Util.DiscordLoger;

@RestController
public class testController {

    @GetMapping("/")
    public String index() {
        return "Truy cập " + "<a href='https://github.com/DoHuy5360/BE_intern' target='_blank'>REST description</a>"
                + "để xem";
    }

    @PostMapping("/login")
    public Response home(HttpServletRequest httpServletRequest) {
        Object jwtToken = (Object) httpServletRequest.getAttribute("jwtToken");
        Object email = (String) httpServletRequest.getAttribute("email");
        new DiscordLoger().prepareContent(String.format("**```%s -> has logged in! ✅```**", email)).send();
        return new Response(HttpStatus.OK, Message.LOGIN_SUCCESS, jwtToken);

    }

    @PostMapping("/decode")
    public String decode() {
        return "Token still alive";
    }
}
