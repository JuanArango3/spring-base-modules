package me.jmarango.security.aspects;

import me.jmarango.security.exceptions.NoAuthenticatedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@SuppressWarnings("unused")
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class RequireAuthAspect {

    // Pointcut for methods annotated with @RequireAuth
    @Pointcut("@annotation(me.jmarango.security.annotations.RequireAuth)")
    public void methodAnnotatedWithRequireAuth() {}

    // Pointcut for classes annotated with @RequireAuth
    @Pointcut("@within(me.jmarango.security.annotations.RequireAuth)")
    public void classAnnotatedWithRequireAuth() {}

    // Advice that runs before methods annotated with @RequireAuth
    @Before("methodAnnotatedWithRequireAuth()")
    public void beforeMethodAnnotated(JoinPoint joinPoint) throws NoAuthenticatedException {
        checkAuth();
    }

    // Advice that runs before methods in classes annotated with @RequireAuth
    @Before("classAnnotatedWithRequireAuth()")
    public void beforeClassAnnotated(JoinPoint joinPoint) throws NoAuthenticatedException {
        checkAuth();
    }

    private void checkAuth() throws NoAuthenticatedException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal == null || principal instanceof AnonymousAuthenticationToken) {
            throw new NoAuthenticatedException("Token inválido");
        }
    }
}
