package com.walkd.dmzing.exception;

public class NotFoundPurchaseHistoryException extends RuntimeException {
    public NotFoundPurchaseHistoryException() {
        super("코스 구매 기록을 찾을 수 없습니다.");
    }
}
