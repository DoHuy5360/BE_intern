package com.example.demo.config.middleware.auth.KIT;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.KIT.RES.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseHandler {
    private ObjectMapper jsonBodyResponse = new ObjectMapper();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Response responseBody;
    private String json;

    public ResponseHandler(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        this.request = request;
        this.response.setContentType("application/json");
        this.response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // Cho phép tất cả các nguồn gốc
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE"); // Cho phép các phương thức POST,
                                                                                      // GET, PUT, DELETE
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Cho phép các tiêu đề
                                                                                           // Content-Type và
                                                                                           // Authorization
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Cho phép sử dụng thông tin đăng nhập của
                                                                        // người dùng
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
