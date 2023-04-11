package com.example.demo.kit.res;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    private HttpStatus status;
    private String message;
    private int amount;
    private Object data;

    public Response(HttpStatus status, String message, int amount, Object data) {
        this.status = status;
        this.message = message;
        this.amount = amount;
        this.data = data;
    }

    public Response(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}