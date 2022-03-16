package com.senla.javaee.service.exception;

import lombok.Getter;

public class CredentialsNotFoundException extends RuntimeException {
    @Getter
    private final Long id;

    public CredentialsNotFoundException(Long id) {
        this.id = id;
    }
}
