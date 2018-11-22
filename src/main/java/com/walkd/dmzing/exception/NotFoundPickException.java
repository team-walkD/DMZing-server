package com.walkd.dmzing.exception;

public class NotFoundPickException extends RuntimeException {
    public NotFoundPickException() {
        super("현재 픽된 맵이 아닙니다.");
    }
}
