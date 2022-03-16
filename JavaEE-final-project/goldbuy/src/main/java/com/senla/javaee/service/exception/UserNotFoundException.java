package com.senla.javaee.service.exception;

import lombok.Getter;

public class UserNotFoundException extends RuntimeException {
    @Getter
    private final String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }

}
