package com.example.userregistration.application.ports.in;

import com.example.userregistration.application.dtos.UserRegistrationRequest;
import com.example.userregistration.application.dtos.UserRegistrationResponse;
import com.example.userregistration.domain.exceptions.UserRegistrationException;

/**
 * Defines the expected operations for the application service to handle user registration,
 * acting as an input port.
 */
public interface UserRegistrationServicePort {

    /**
     * Attempts to register a new user in the system using the provided user registration request data.
     * This method is responsible for orchestrating the registration process and will return a
     * UserRegistrationResponse that includes details about the outcome of the registration.
     * It may throw a UserRegistrationException if any issues arise during the registration process,
     * such as validation failures or persistence errors.
     *
     * @param userRegistrationRequest the user registration request data
     * @return the response of the user registration process
     * @throws UserRegistrationException if there are issues with user registration
     */
    UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) throws UserRegistrationException;

    /**
     * Validates the given UserRegistrationRequest to ensure that all necessary information is
     * present and correct before proceeding with user registration.
     * Throws a UserRegistrationException if the request does not meet the required criteria.
     *
     * @param userRegistrationRequest the user registration request to validate
     * @throws UserRegistrationException if the validation fails
     */
    void validateUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) throws UserRegistrationException;

}