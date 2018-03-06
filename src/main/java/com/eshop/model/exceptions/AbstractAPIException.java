package com.eshop.model.exceptions;

import com.eshop.model.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractAPIException extends Exception {

    private ErrorResponse errorResponse;
    private BindingResult result;

    public AbstractAPIException() {
    }


    public AbstractAPIException(BindingResult result) {
        this.result = result;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ErrorResponse    getErrorResponse() {
        if (this.errorResponse == null) {
            this.errorResponse = new ErrorResponse();
        }
        if (result != null) {
            List<FieldError> fieldErrors = this.result.getFieldErrors();
            Map<String, List<String>> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.putIfAbsent(fieldError.getField(), new ArrayList<>());
                errors.get(fieldError.getField()).add(fieldError.getDefaultMessage());
            }
            this.errorResponse.setErrors(errors);
            return this.errorResponse;
        }
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("Error", new ArrayList<>());
        errors.get("Error").add(getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errors);
        return errorResponse;
    }

    public HttpStatus getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

}