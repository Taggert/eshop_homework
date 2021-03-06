package com.eshop.model.exceptions;

import org.springframework.http.HttpStatus;


public class NotEnoughProductsException extends GeneralAPIException {

    public NotEnoughProductsException() {
    }

    public NotEnoughProductsException(String message) {
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