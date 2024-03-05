package com.example.userregistration.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationException extends RuntimeException {

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

}