package com.example.userregistration.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Objects;

public class UserRegistrationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private boolean success;

    @NotEmpty
    @JsonProperty
    private String message;

    public UserRegistrationResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationResponse that = (UserRegistrationResponse) o;
        return success == that.success &&
               Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message);
    }

    @Override
    public String toString() {
        return "UserRegistrationResponse{" +
               "success=" + success +
               ", message='" + message + '\'' +
               '}';
    }
}