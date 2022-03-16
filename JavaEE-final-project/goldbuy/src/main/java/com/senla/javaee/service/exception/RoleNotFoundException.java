package com.senla.javaee.service.exception;

import lombok.Getter;

public class RoleNotFoundException extends RuntimeException {
    @Getter
    private final Long id;

    public RoleNotFoundException(Long id) {
        this.id = id;
    }
}
