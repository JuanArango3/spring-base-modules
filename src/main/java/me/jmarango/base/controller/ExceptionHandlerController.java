package me.jmarango.base.controller;

import me.jmarango.base.dto.response.BasicResponse;
import me.jmarango.base.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BasicResponse handleValidationException(MethodArgumentNotValidException ex) {
        return new BasicResponse(false, String.join(", ", ex.getBindingResult().getFieldErrors().stream().map(FieldError::getField).toList()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public BasicResponse handleValidationException(IllegalArgumentException ex) {
        return new BasicResponse(false, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public BasicResponse handleEntityNotFoundException(NotFoundException ex) {
        return new BasicResponse(false, ex.getMessage());
    }
}
