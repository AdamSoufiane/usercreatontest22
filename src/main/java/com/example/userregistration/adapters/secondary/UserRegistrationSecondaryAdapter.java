package com.example.userregistration.adapters.secondary;

import com.example.userregistration.application.ports.out.UserRegistrationOutputPort;
import com.example.userregistration.application.dtos.UserRegistrationResponse;
import com.example.userregistration.domain.entities.UserEntity;
import com.example.userregistration.domain.exceptions.UserRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationSecondaryAdapter implements UserRegistrationOutputPort {

    private final UserRegistrationNotificationService notificationService;

    @Override
    public void presentRegistrationResult(UserRegistrationResponse response) throws UserRegistrationException {
        UserEntity userEntity = response.getUser();
        notificationService.sendConfirmationEmail(userEntity);
    }
}
