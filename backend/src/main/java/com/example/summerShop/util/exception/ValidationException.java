package com.example.summerShop.util.exception;

import com.example.summerShop.util.response.ErrorDto;

import java.util.List;

public class ValidationException extends jakarta.validation.ValidationException {

    private final List<ErrorDto> info;

    public ValidationException(String message, List<ErrorDto> info) {
        super(message);
        this.info = info;
    }

    public ValidationException(List<ErrorDto> info) {
        this.info = info;
    }

    public ValidationException(String message, Throwable cause, List<ErrorDto> info) {
        super(message, cause);
        this.info = info;
    }

    public ValidationException(Throwable cause, List<ErrorDto> info) {
        super(cause);
        this.info = info;
    }

    public List<ErrorDto> getInfo() {
        return info;
    }
}
