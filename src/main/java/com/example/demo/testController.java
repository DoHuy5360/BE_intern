package com.example.demo;

import java.text.MessageFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.KIT.JWT.JwtHandler;
import com.example.demo.KIT.RES.Message;
import com.example.demo.KIT.RES.Response;
import com.example.demo.KIT.Util.DiscordLoger;

@RestController
// @CrossOrigin("*")
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
        new DiscordLoger().prepareContent(String.format("**```%s -> has logged in!💦```**", email)).send();
        return new Response(HttpStatus.OK, Message.LOGIN_SUCCESS, jwtToken);

    }

    @PostMapping("/decode")
    // public void decode(@RequestHeader("Authorization") String
    // authorizationHeader) {
    public String decode() {
        return "Token still alive";
    }
}
