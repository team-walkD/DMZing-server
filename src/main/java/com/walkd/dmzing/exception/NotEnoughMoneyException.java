package com.walkd.dmzing.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("돈이 부족합니다.");
    }
}
