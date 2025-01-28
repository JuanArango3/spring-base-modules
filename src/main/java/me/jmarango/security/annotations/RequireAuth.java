package me.jmarango.security.annotations;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import me.jmarango.security.annotations.documentation.InvalidAuth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SecurityRequirement(name = "auth")
@InvalidAuth
public @interface RequireAuth {
}
