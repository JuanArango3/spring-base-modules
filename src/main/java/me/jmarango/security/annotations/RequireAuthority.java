package me.jmarango.security.annotations;

import me.jmarango.security.annotations.documentation.InsufficientPermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RequireAuth
@InsufficientPermission
public @interface RequireAuthority {
    String value();
}
