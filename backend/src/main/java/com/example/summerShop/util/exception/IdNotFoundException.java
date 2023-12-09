package com.example.summerShop.util.exception;

public class IdNotFoundException extends IllegalArgumentException {
    public IdNotFoundException() {
    }

    public IdNotFoundException(String s) {
        super(s);
    }

    public IdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotFoundException(Throwable cause) {
        super(cause);
    }
}
