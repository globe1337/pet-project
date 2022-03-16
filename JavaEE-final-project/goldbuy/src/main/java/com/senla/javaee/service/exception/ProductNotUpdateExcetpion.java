package com.senla.javaee.service.exception;

import lombok.Getter;

public class ProductNotUpdateExcetpion extends RuntimeException {
    @Getter
    private final Long id;
    public ProductNotUpdateExcetpion(Long id) {
        this.id=id;
    }
}
