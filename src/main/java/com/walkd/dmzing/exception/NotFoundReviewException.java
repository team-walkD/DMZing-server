package com.walkd.dmzing.exception;

public class NotFoundReviewException extends RuntimeException {
    public NotFoundReviewException() {
        super("해당 리뷰가 없습니다.");
    }
}
