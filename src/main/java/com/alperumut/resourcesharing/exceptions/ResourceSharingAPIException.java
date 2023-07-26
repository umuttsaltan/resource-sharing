package com.alperumut.resourcesharing.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceSharingAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ResourceSharingAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResourceSharingAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }


    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
