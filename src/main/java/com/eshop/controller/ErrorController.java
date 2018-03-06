package com.eshop.controller;

import com.eshop.model.exceptions.AbstractAPIException;
import com.eshop.model.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = AbstractAPIException.class)
    public ResponseEntity<ErrorResponse> getErrorResponse(AbstractAPIException e){
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

}