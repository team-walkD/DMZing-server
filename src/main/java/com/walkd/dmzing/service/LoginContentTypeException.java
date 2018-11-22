package com.walkd.dmzing.service;


import org.springframework.security.core.AuthenticationException;

public class LoginContentTypeException extends AuthenticationException {
    public LoginContentTypeException(String msg) {
        super(msg);
    }
}
