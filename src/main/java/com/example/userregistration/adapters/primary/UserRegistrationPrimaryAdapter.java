package com.example.userregistration.adapters.primary;

import com.example.userregistration.application.dtos.UserRegistrationRequest;
import com.example.userregistration.application.dtos.UserRegistrationResponse;
import com.example.userregistration.application.ports.in.UserRegistrationServicePort;
import com.example.userregistration.domain.exceptions.UserRegistrationException;
import com.example.userregistration.domain.exceptions.ErrorDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class UserRegistrationPrimaryAdapter {

    private final UserRegistrationServicePort userRegistrationServicePort;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserRegistrationRequest request) throws UserRegistrationException {
        UserRegistrationResponse response = userRegistrationServicePort.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ExceptionHandler(UserRegistrationException.class)
    public final ResponseEntity<Object> handleUserRegistrationException(UserRegistrationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toEpochMilli(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}