package eu.senla.ecabtask.producer.controller.exceptionhandler;

import eu.senla.ecabtask.exception.BookingNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ECabExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleAllUncaughtException(Exception exception) {
        log.error("SYSTEM ERROR: " + exception.getMessage());
    }


    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected void handleMethodArgumentNotValid(BookingNotFoundException exception) {
        log.error("BOOKING NOT FOUND: " + exception.getMessage());
    }
}
