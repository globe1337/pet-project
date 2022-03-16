package com.senla.javaee.service.exception;

import lombok.Getter;

public class ProductConfigurationNotFoundException extends RuntimeException {
    @Getter
    private final Long id;

    public ProductConfigurationNotFoundException(Long id) {
        this.id = id;
    }
}
