package com.walkd.dmzing.exception;

public class AlreadySuccessedException extends RuntimeException {
    public AlreadySuccessedException() {
        super("이미 해결한 미션입니다.");
    }
}
