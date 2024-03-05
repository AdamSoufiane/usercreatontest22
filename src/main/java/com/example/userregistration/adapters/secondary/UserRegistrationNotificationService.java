package com.example.userregistration.adapters.secondary;

import com.example.userregistration.domain.entities.UserEntity;
import com.example.userregistration.domain.exceptions.UserRegistrationException;
import com.example.userregistration.infrastructure.email.EmailService;
import com.example.userregistration.infrastructure.email.EmailServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationNotificationService {

    private final EmailService emailService;

    public void sendConfirmationEmail(UserEntity user) {
        String subject = "Registration Confirmation";
        String body = String.format("Hello %s,\n\nThank you for registering. Please confirm your email address.", user.getName());
        try {
            emailService.sendEmail(user.getEmail(), subject, body);
        } catch (EmailServiceException e) {
            throw new UserRegistrationException("Failed to send registration confirmation email.", e);
        }
    }
}
