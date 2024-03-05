package com.example.userregistration.application.services;

import com.example.userregistration.application.dtos.UserRegistrationRequest;
import com.example.userregistration.application.dtos.UserRegistrationResponse;
import com.example.userregistration.application.ports.in.UserRegistrationServicePort;
import com.example.userregistration.application.ports.out.UserRegistrationOutputPort;
import com.example.userregistration.domain.entities.UserEntity;
import com.example.userregistration.domain.exceptions.UserRegistrationException;
import com.example.userregistration.domain.ports.UserRepositoryPort;
import com.example.userregistration.domain.services.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationApplicationService implements UserRegistrationServicePort {

    private final UserRepositoryPort userRepository;
    private final UserDomainService userDomainService;
    private final UserRegistrationOutputPort registrationOutputPort;

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) throws UserRegistrationException {
        UserEntity userEntity = request.toUserEntity();
        if (userDomainService.validateUser(userEntity)) {
            userDomainService.enforceRegistrationRules(userEntity);
            try {
                UserEntity savedUser = userRepository.saveUser(userEntity);
                UserRegistrationResponse response = new UserRegistrationResponse(savedUser.getId(), "Registration successful");
                registrationOutputPort.presentRegistrationResult(response);
                return response;
            } catch (DataAccessException e) {
                throw new UserRegistrationException("Error during user registration", e);
            }
        } else {
            throw new UserRegistrationException("User validation failed");
        }
    }
}