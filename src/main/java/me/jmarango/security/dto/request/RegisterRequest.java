package me.jmarango.security.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Schema(minLength = 4, maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    @Length(min = 4, max = 20)
    @NotBlank
    private String username;

    @Schema(minLength = 4, requiredMode = Schema.RequiredMode.REQUIRED)
    @Length(min = 4)
    @NotBlank
    private String password;
}
