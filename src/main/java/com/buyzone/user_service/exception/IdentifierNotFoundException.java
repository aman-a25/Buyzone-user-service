package com.buyzone.user_service.exception;

public class IdentifierNotFoundException extends RuntimeException {
    public IdentifierNotFoundException(String message) {
        super(message);
    }
}
