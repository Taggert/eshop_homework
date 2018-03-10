package com.eshop.model.exceptions;

import org.springframework.http.HttpStatus;

public class InsufficiendFundsException extends GeneralAPIException {

    public InsufficiendFundsException() {
    }

    public InsufficiendFundsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.PAYMENT_REQUIRED;
    }

    @Override
    public Object getErrors() {
        return super.getErrors();
    }
}