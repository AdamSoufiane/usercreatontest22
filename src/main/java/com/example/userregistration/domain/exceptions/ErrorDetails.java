package com.example.userregistration.domain.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDetails {

    private final long timestamp;
    private final String message;
    private final int errorCode;

}