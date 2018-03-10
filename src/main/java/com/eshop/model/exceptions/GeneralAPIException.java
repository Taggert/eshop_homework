package com.eshop.model.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralAPIException extends RuntimeException {

    public GeneralAPIException() {
    }

    public GeneralAPIException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public Object getErrors() {
        return super.getMessage();
    }
}
