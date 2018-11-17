package com.walkd.dmzing.exception;

public class NotMatchedCourseException extends RuntimeException{
    public NotMatchedCourseException() {
        super("해당 코스와 매칭되지 않는 장소입니다.");
    }
}
