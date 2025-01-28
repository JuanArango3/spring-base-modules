package me.jmarango.security.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    @Schema(example = "Juan", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank
    @Schema(example = "jg21akshf21", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Si se activa el modo dev, el token durará 30 segundos",requiredMode = Schema.RequiredMode.NOT_REQUIRED, defaultValue = "false")
    private boolean dev=false;
}
