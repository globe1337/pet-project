package com.senla.javaee.controller.handler.dto;

import lombok.Builder;

@Builder
public class ErrorMessageDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
