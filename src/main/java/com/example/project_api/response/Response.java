package com.example.project_api.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private HttpStatus status;
    private String message;
    
    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
