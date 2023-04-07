package com.example.demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.KIT.JWT.JwtHandler;
import com.example.demo.KIT.JWT.JwtToken;
import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;

@RestController
@CrossOrigin("*")
public class testController {
    @GetMapping("/")
    public String index() {
        return "Truy cập " + "<a href='https://github.com/DoHuy5360/BE_intern' target='_blank'>REST description</a>"
                + "để xem";
    }

    @PostMapping("/login")
    public Response home(HttpServletResponse httpServletResponse) {

        return new Response(HttpStatus.OK, Message.LOGIN_SUCCESS);
    }

    @PostMapping("/decode")
    // public void decode(@RequestHeader("Authorization") String
    // authorizationHeader) {
    public String decode() {
        return "Hell";
    }
}
