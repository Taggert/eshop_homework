package com.eshop.model.exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchProductException extends GeneralAPIException {

    public NoSuchProductException() {
    }

    public NoSuchProductException(String message) {
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