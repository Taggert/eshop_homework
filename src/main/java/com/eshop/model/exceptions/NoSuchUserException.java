package com.eshop.model.exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchUserException extends GeneralAPIException {

    public NoSuchUserException() {
    }

    public NoSuchUserException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public Object getErrors() {
        return super.getErrors();
    }
}