package me.jmarango.base.documentation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.jmarango.base.dto.response.BasicResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(
        @ApiResponse(responseCode = "400", description = "Request body no válido (por la validación de parámetros)", content = @Content(schema = @Schema(implementation = BasicResponse.class)))
)
public @interface RequestBodyNotValidResponse {
}

