package com.example.userregistration.domain.services;

import com.example.userregistration.domain.entities.UserEntity;
import com.example.userregistration.domain.ports.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains business logic for handling user-related operations, such as validating user properties and enforcing registration rules.
 */
@Service
public class UserDomainService {

    private final UserRepositoryPort userRepositoryPort;

    @Autowired
    public UserDomainService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public boolean validateUser(UserEntity user) throws DataAccessException {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(user.getEmail()).matches()) {
            return false;
        }
        try {
            Optional<UserEntity> existingUser = userRepositoryPort.findUserByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return false;
            }
        } catch (DataAccessException e) {
            throw e;
        }
        String nameRegex = "^[\\p{L} .'-]+$";
        return Pattern.compile(nameRegex).matcher(user.getName()).matches();
    }

    public void enforceRegistrationRules(UserEntity user) throws DataAccessException {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        if (!isPasswordStrong(user.getPassword())) {
            throw new IllegalArgumentException("Password does not meet strength requirements");
        }
        if (!isAgeValid(user.getDateOfBirth())) {
            throw new IllegalArgumentException("User does not meet age requirements");
        }
    }

    private boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }
        return hasLetter && hasDigit && hasSpecial;
    }

    private boolean isAgeValid(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }
}
