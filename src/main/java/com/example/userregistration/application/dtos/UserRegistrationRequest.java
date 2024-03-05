package com.example.userregistration.application.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * A data transfer object that contains the necessary information for registering a new user, as received from the client.
 */
@Data
@NoArgsConstructor
public class UserRegistrationRequest implements IUserRegistrationRequest {

    private static final long serialVersionUID = 8390281105469744889L;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotBlank(message = "Confirm password is required.")
    private String confirmPassword;

    @NotNull(message = "Creation timestamp is required.")
    private Instant created;

    @NotNull(message = "Modification timestamp is required.")
    private Instant modified;

    public UserRegistrationRequest(String name, String email, String password, String confirmPassword, Instant created, Instant modified) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.created = created;
        this.modified = modified;
    }
}