package com.example.summerShop.util.exception;

public class NonExistentIdException extends IllegalArgumentException {

    public NonExistentIdException() {
    }

    public NonExistentIdException(String s) {
        super(s);
    }

    public NonExistentIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentIdException(Throwable cause) {
        super(cause);
    }
}
