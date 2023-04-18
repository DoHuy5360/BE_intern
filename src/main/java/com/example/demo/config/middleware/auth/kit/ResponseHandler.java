package com.example.demo.config.middleware.auth.kit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.example.demo.kit.res.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class ResponseHandler {
    private ObjectMapper jsonBodyResponse = new ObjectMapper();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Response responseBody;
    private String json;

    public void createResponse(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        this.request = request;
        this.response.setContentType("application/json");
        this.response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    public ResponseHandler setContent(Response responseBody) {
        this.responseBody = responseBody;
        return this;
    }

    public void send() {
        try {
            this.json = this.jsonBodyResponse
                    .writeValueAsString(this.responseBody);
            PrintWriter printWriter = this.response.getWriter();
            printWriter.write(json);
            printWriter.flush();
        } catch (JsonProcessingException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
