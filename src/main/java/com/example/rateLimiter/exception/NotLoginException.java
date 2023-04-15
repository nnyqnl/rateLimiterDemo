package com.example.rateLimiter.exception;

public class NotLoginException extends RuntimeException {
    public NotLoginException(String message) {
        super(message);
    }
}
