package me.jmarango.security.exceptions;

public class InsufficientPermissionException extends RuntimeException {
    public InsufficientPermissionException(String msg) {
        super(msg);
    }
}
