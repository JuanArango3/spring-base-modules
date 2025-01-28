package me.jmarango.security.aspects;


import lombok.RequiredArgsConstructor;
import me.jmarango.security.annotations.RequireAuthority;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@SuppressWarnings("unused")
public class RequireAuthorityAspect {


    // Pointcut for methods annotated with @RequireAuthority
    @Pointcut("@annotation(me.jmarango.security.annotations.RequireAuthority)")
    public void methodAnnotatedWithRequireAuthority() {}

    // Pointcut for classes annotated with @RequireAuthority
    @Pointcut("@within(me.jmarango.security.annotations.RequireAuthority)")
    public void classAnnotatedWithRequireAuthority() {}

    // Advice that runs before methods annotated with @RequireAuthority
    @Before("methodAnnotatedWithRequireAuthority()")
    public void beforeMethodAnnotated(JoinPoint joinPoint) throws InsufficientAuthenticationException {
        checkAuthority(joinPoint);
    }

    // Advice that runs before methods in classes annotated with @RequireAdmin
    @Before("classAnnotatedWithRequireAuthority()")
    public void beforeClassAnnotated(JoinPoint joinPoint) throws InsufficientAuthenticationException {
        checkAuthority(joinPoint);
    }

    private void checkAuthority(JoinPoint jp) throws InsufficientAuthenticationException {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();

        RequireAuthority annotation = method.getAnnotation(RequireAuthority.class);

        if (annotation == null) {
            Class<?> targetClass = jp.getTarget().getClass();
            annotation = targetClass.getAnnotation(RequireAuthority.class);
        }

        if (annotation == null) throw new IllegalStateException("No @RequireAuthority annotation found"); // XD

        String requiredAuthority = annotation.value();

        if (!hasRequiredAuthority(requiredAuthority)) {
            throw new InsufficientAuthenticationException("Insufficient permissions to access this method");
        }


    }

    private boolean hasRequiredAuthority(String requiredAuthority) {
        // Verificar si el usuario autenticado tiene la autoridad requerida
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().parallelStream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase(requiredAuthority));
    }

}
