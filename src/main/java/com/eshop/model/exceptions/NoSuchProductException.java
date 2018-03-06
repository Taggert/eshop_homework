package com.eshop.model.exceptions;

import com.eshop.model.web.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class NoSuchProductException extends AbstractAPIException {

    private String error;

    @Override
    public ErrorResponse getErrorResponse(){
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("Error", new ArrayList<>());
        errors.get("Error").add(error);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errors);
        return errorResponse;
    }

    @Override
    public String getMessage() {
        return error;
    }


}