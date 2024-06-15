package com.ra.projectspringboot.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}