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

@RestController
// @CrossOrigin("*")
public class testController {
    public RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String index() {
        return "Truy cập " + "<a href='https://github.com/DoHuy5360/BE_intern' target='_blank'>REST description</a>"
                + "để xem";
    }

    @PostMapping("/login")
    public Response home(HttpServletRequest httpServletRequest) {
        Object jwtToken = (Object) httpServletRequest.getAttribute("jwtToken");
        Object email = (String) httpServletRequest.getAttribute("email");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = String.format("{\"content\": \"**```%s -> has logged in! 💦```**\" }", email);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        String webhookId = "1095224498556510258";
        String webhookToken = "DJXaInfYDEijijdfPKx30rNv5xlX4mTCvQ501W_ALb6fKI59I7t16DraTl7kMN6BE1OL";
        String channelId = "1095224215076077650";
        restTemplate.postForEntity(
                "https://discord.com/api/v10/webhooks/" + webhookId
                        + "/" + webhookToken + "?channel_id=" + channelId + "&wait=true",
                entity, String.class);

        return new Response(HttpStatus.OK, Message.LOGIN_SUCCESS, jwtToken);

    }

    @PostMapping("/decode")
    // public void decode(@RequestHeader("Authorization") String
    // authorizationHeader) {
    public String decode() {
        return "Token still alive";
    }
}
