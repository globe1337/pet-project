package com.senla.javaee.controller.handler;

import com.senla.javaee.controller.handler.dto.ErrorMessageDto;
import com.senla.javaee.service.exception.*;
import com.senla.javaee.service.exception.category.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorMessageDto errorMessageDto(CategoryNotFoundException categoryNotFoundException) {
        return ErrorMessageDto.builder()
                .name("Категория " + categoryNotFoundException.getMessage() + " не найден").build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HistoryNotFoundException.class)
    public ErrorMessageDto errorMessageDto(HistoryNotFoundException historyNotFoundException) {
        return ErrorMessageDto.builder()
                .name("История с id=" + historyNotFoundException.getId() + " не найден").build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductConfigurationNotFoundException.class)
    public ErrorMessageDto errorMessageDto(ProductConfigurationNotFoundException productConfigurationNotFoundException) {
        return ErrorMessageDto.builder()
                .name("Конфигурация продукта с id=" + productConfigurationNotFoundException.getId() + " не найден").build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorMessageDto errorMessageDto(ProductNotFoundException productNotFoundException) {
        return ErrorMessageDto.builder()
                .name("Продукт с id=" + productNotFoundException.getId() + " не найден").build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotUpdateExcetpion.class)
    public ErrorMessageDto errorMessageDto(ProductNotUpdateExcetpion productNotFoundException) {
        return ErrorMessageDto.builder()
                .name("Продукт с id=" + productNotFoundException.getId() + " не обновлен").build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ErrorMessageDto errorMessageDto(RoleNotFoundException roleNotFoundException) {
        return ErrorMessageDto.builder()
                .name("Роль с id=" + roleNotFoundException.getId() + " не найден").build();
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorMessageDto errorMessageDto(UserNotFoundException userNotFoundException) {
        return ErrorMessageDto.builder()
                .name("Пользователь " + userNotFoundException.getMessage() + " не найден").build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorMessageDto catchRuntimeException(RuntimeException e) {
        return ErrorMessageDto.builder()
                .name("что-то пошло не так").build();
    }

}
