package com.example.userregistration.application.ports.out;

import com.example.userregistration.application.dtos.UserRegistrationResponse;
import com.example.userregistration.domain.exceptions.UserRegistrationException;

/**
 * Outlines the output operations for the user registration process, such as presenting the result of the registration.
 */
public interface UserRegistrationOutputPort {

    /**
     * Presents the result of the user registration process.
     * @param response the user registration response
     * @throws UserRegistrationException if there is an issue in presenting the registration result
     */
    void presentRegistrationResult(UserRegistrationResponse response) throws UserRegistrationException;

}