package com.example.userregistration.domain.exceptions;

public class UserDomainPersistenceException extends RuntimeException {
    public UserDomainPersistenceException(String message) {
        super(message);
    }

    public UserDomainPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
