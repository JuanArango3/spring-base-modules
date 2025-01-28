package me.jmarango.security.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Schema(minLength = 4, maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 4, max = 20)
    @NotBlank
    private String username;

    @Schema(minLength = 4, requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(4)
    @NotBlank
    private String password;
}
