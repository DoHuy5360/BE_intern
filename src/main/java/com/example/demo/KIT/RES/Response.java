package com.example.demo.KIT.RES;

import org.springframework.http.HttpStatus;

public class Response {

    private HttpStatus status;
    private String message;
    private Object entity;

    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(HttpStatus status, String message, Object entity) {
        this.status = status;
        this.message = message;
        this.entity = entity;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

}