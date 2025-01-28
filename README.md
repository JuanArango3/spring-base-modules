# Spring Base Modules

## Description
Spring Base Modules is a Java library that provides a set of base modules for building Spring Boot applications. It includes various features and dependencies to facilitate the development of secure, web-based, and data-driven applications. This library is designed to facilitate the development of my personal projects.

## Features
- **Spring Boot Integration**: Leverages Spring Boot for rapid application development.
- **Web Support**: Includes Spring Boot Starter Web for building web applications.
- **Security**: Integrates Spring Boot Starter Security for securing applications.
- **Validation**: Utilizes Spring Boot Starter Validation for input validation.
- **JWT Support**: Includes `jjwt` for JSON Web Token (JWT) handling.
- **Data Access**: Provides Spring Boot Starter Data JPA for database interactions.
- **Configuration Processing**: Uses Spring Boot Configuration Processor for type-safe configuration properties.
- **Aspect-Oriented Programming (AOP)**: Integrates Spring Boot Starter AOP for aspect-oriented programming.
- **Lombok**: Utilizes Lombok for reducing boilerplate code.
- **Testing**: Includes Spring Boot Starter Test for testing support.
- **OpenAPI Documentation**: Integrates SpringDoc OpenAPI for API documentation.
- **JAXB Support**: Provides JAXB API for XML binding.

## Usage
To use this library, add the following dependency to your project:

```xml
<dependency>
    <groupId>me.jmarango</groupId>
    <artifactId>spring-base-modules</artifactId>
    <version>0.0.4-SNAPSHOT</version>
</dependency>
```

Additionally, add the GitHub Maven repository to your project:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/JuanArango3/spring-base-modules</url>
    </repository>
</repositories>
```