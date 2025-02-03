package me.jmarango.security.exceptions;

import me.jmarango.base.exception.code.UnauthorizedException;

public class NoAuthenticatedException extends UnauthorizedException {
    public NoAuthenticatedException(String msg) {
        super(msg);
    }
}
