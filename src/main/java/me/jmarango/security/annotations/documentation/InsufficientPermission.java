package me.jmarango.security.annotations.documentation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.jmarango.base.dto.response.BasicResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(
        @ApiResponse(responseCode = "403", description = "Se intenta acceder a un recurso protegido por permisos y no los tiene", content = @Content(schema = @Schema(implementation = BasicResponse.class)))
)
public @interface InsufficientPermission {
}
