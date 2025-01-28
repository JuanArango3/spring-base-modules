package me.jmarango.base.exception;

public class UserWithUsernameAlreadyExistsException extends Exception {
    public UserWithUsernameAlreadyExistsException(String username) {
        super(username);
    }
}
