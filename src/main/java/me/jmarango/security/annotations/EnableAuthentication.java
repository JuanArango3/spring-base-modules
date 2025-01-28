package me.jmarango.security.annotations;

import me.jmarango.security.config.ApplicationSecurity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ApplicationSecurity.class)
@ComponentScan
public @interface EnableAuthentication {
}
