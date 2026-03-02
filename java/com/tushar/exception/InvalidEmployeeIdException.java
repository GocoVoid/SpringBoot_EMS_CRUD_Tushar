package com.tushar.exception;

public class InvalidEmployeeIdException extends RuntimeException {
    public InvalidEmployeeIdException(String message) {
        super(message);
    }
}