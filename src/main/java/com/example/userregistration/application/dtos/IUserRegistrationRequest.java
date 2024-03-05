package com.example.userregistration.application.dtos;

import java.io.Serializable;
import java.time.Instant;

public interface IUserRegistrationRequest extends Serializable {
    String getName();
    String getEmail();
    String getPassword();
    String getConfirmPassword();
    Instant getCreated();
    Instant getModified();
}