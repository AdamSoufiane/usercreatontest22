package com.example.userregistration.infrastructure.email;

import com.example.userregistration.domain.exceptions.EmailServiceException;

/**
 * Interface that defines the contract for sending emails. It includes a method signature for sending emails,
 * which is required by the UserRegistrationSecondaryAdapter.
 */
public interface EmailService {

    /**
     * Sends an email to the specified recipient with the given subject and body.
     *
     * @param to      the recipient's email address
     * @param subject the subject of the email
     * @param body    the body of the email
     * @throws EmailServiceException if there is an error sending the email
     */
    void sendEmail(String to, String subject, String body) throws EmailServiceException;

}