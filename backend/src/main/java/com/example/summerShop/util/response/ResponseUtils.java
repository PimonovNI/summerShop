package com.example.summerShop.util.response;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ResponseUtils {

    public static List<ErrorDto> createErrorsResponse(Errors errors) {
        ArrayList<ErrorDto> res = new ArrayList<>(errors.getErrorCount());
        for (FieldError error : errors.getFieldErrors()) {
            res.add(new ErrorDto(
                    error.getField(),
                    error.getDefaultMessage()
            ));
        }
        return res;
    }

}
