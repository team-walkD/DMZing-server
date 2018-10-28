package com.walkd.dmzing.exception;

public class AlreadySaveImageException extends RuntimeException {
    public AlreadySaveImageException() {
        super("이미 db에 저장된 이미지입니다.");
    }
}
