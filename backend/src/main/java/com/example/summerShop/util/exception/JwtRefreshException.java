package com.example.summerShop.util.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtRefreshException extends AuthenticationException {
    public JwtRefreshException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtRefreshException(String msg) {
        super(msg);
    }
}
