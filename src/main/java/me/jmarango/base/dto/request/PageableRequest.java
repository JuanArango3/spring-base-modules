package me.jmarango.base.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableRequest {
    @Schema(defaultValue = "0", description = "Página actual")
    @Min(0)
    private int page=0;

    @Schema(defaultValue = "20", description = "Tamaño de la página")
    @Min(1)
    @Max(1000)
    private int size=20;
}
