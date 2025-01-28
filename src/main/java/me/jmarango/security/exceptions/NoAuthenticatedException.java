package me.jmarango.security.exceptions;

public class NoAuthenticatedException extends RuntimeException {
    public NoAuthenticatedException(String msg) {
        super(msg);
    }
}
