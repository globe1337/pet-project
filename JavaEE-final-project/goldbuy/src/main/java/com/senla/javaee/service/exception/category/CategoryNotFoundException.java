package com.senla.javaee.service.exception.category;

import lombok.Getter;

public class CategoryNotFoundException extends RuntimeException {
    @Getter
    private final String message;

    public CategoryNotFoundException(Long id) {
        this.message = " id :"+String.valueOf(id);
    }

    public CategoryNotFoundException(String message) {
        this.message ="name :"+ message;
    }

}
