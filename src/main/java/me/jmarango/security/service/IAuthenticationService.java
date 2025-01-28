package me.jmarango.security.service;

import me.jmarango.base.exception.UserWithUsernameAlreadyExistsException;
import me.jmarango.security.dto.request.LoginRequest;
import me.jmarango.security.dto.request.RegisterRequest;
import me.jmarango.security.dto.response.TokenResponse;
import me.jmarango.security.exceptions.NoAuthenticatedException;
import org.springframework.security.authentication.BadCredentialsException;

import java.security.Principal;

public interface IAuthenticationService {
    <T extends LoginRequest> TokenResponse authenticate(T request) throws BadCredentialsException;
    TokenResponse refreshToken(Principal principal, boolean isShortDuration) throws NoAuthenticatedException;
    <T extends RegisterRequest> TokenResponse register(T request) throws UserWithUsernameAlreadyExistsException;
}
