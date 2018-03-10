package com.eshop.model.exceptions;

import org.springframework.http.HttpStatus;


public class NotYourProductException extends GeneralAPIException {

    public NotYourProductException() {
    }

    public NotYourProductException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @Override
    public Object getErrors() {
        return super.getErrors();
    }


}