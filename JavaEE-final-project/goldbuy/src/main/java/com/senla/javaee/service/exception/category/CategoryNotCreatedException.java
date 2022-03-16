package com.senla.javaee.service.exception.category;

import lombok.Getter;

public class CategoryNotCreatedException extends RuntimeException {
    @Getter
    private final String name;

    public CategoryNotCreatedException(String name) {
        this.name=name;


    }
}