package com.main.app.errorhandler;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
