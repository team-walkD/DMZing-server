package com.walkd.dmzing.exception;

public class NotFoundCourseException extends RuntimeException {
    public NotFoundCourseException(){
        super("코스를 찾을 수 없습니다.");
    }
}
