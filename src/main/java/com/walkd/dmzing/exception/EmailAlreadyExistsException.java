package com.walkd.dmzing.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("email already exists");
    }
}