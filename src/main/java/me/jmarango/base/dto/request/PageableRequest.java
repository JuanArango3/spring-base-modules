package me.jmarango.base.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jmarango.base.exception.code.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
 * Clase que representa una solicitud paginada.
 * Permite definir la página actual, el tamaño de la página y el ordenamiento.
 */
public class PageableRequest {
    @Schema(defaultValue = "0", description = "Página actual")
    @Min(0)
    @Builder.Default
    private int page=0;

    @Schema(defaultValue = "20", description = "Tamaño de la página")
    @Min(1)
    @Max(1000)
    @Builder.Default
    private int size=20;

    @Schema(description = "Ordenamiento de los resultados. Formato: 'campo,direccion'. Ejemplo: 'nombre,asc' o 'fecha,desc'.")
    private List<String> sort;

    public PageRequest toPageRequest() {
        if (sort == null || sort.isEmpty()) {
            return PageRequest.of(page, size);
        }
        return PageRequest.of(page, size, Sort.by(sort.stream().map(s -> {
            String[] parts = s.split(",");
            String property = parts[0].trim();
            if (parts.length == 1) {
                return new Sort.Order(Sort.Direction.ASC, property);
            } else if (parts.length == 2) {
                return new Sort.Order(Sort.Direction.fromString(parts[1]), property);
            } else {
                throw new BadRequestException("Invalid sort format: " + s);
            }
        }).toList()));
    }
}
