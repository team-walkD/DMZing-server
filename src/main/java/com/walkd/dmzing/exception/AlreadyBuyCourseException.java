package com.walkd.dmzing.exception;

public class AlreadyBuyCourseException extends RuntimeException {
    public AlreadyBuyCourseException() {
        super("이미 구매한 맵입니다.");
    }
}
