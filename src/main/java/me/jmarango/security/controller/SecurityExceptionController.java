package me.jmarango.security.controller;

import me.jmarango.base.dto.response.BasicResponse;
import me.jmarango.security.exceptions.InsufficientPermissionException;
import me.jmarango.security.exceptions.NoAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionController {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoAuthenticatedException.class)
    public BasicResponse handleNoAuthenticatedException(NoAuthenticatedException e) {
        return new BasicResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InsufficientPermissionException.class)
    public BasicResponse handleInsufficientPermissionException(InsufficientPermissionException e) {
        return new BasicResponse(e.getMessage());
    }
}
