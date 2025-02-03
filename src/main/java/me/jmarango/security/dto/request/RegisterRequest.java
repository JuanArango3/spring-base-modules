package me.jmarango.security.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class RegisterRequest {

    @Schema(minLength = 4, maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    @Size(min = 4, max = 20)
    @NotBlank
    protected String username;

    @Schema(minLength = 4, requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 4)
    @NotBlank
    protected String password;
}
