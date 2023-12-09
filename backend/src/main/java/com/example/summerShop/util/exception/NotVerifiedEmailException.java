package com.example.summerShop.util.exception;

import org.springframework.security.core.AuthenticationException;

public class NotVerifiedEmailException extends AuthenticationException {
    public NotVerifiedEmailException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NotVerifiedEmailException(String msg) {
        super(msg);
    }
}
