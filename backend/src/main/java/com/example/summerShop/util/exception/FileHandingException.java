package com.example.summerShop.util.exception;

import java.io.IOException;

public class FileHandingException extends IOException {
    public FileHandingException() {
    }

    public FileHandingException(String message) {
        super(message);
    }

    public FileHandingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileHandingException(Throwable cause) {
        super(cause);
    }
}