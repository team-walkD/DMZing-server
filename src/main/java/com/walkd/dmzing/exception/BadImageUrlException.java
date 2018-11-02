package com.walkd.dmzing.exception;

public class BadImageUrlException extends RuntimeException {
    public BadImageUrlException() {
        super("잘못된 이미지 경로입니다.");
    }
}
