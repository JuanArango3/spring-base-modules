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
import java.util.Set;

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

    /**
     * Convierte los parámetros de paginación y ordenamiento a un PageRequest de Spring Data.
     *
     * @return PageRequest construido a partir de los parámetros de paginación y ordenamiento.
     */
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

    /**
     * Valida los campos de ordenamiento permitidos y construye un PageRequest.
     *
     * @param allowedSortFields Conjunto de campos de ordenamiento permitidos.
     * @return PageRequest construido a partir de los parámetros de paginación y ordenamiento.
     * @throws BadRequestException Si algún campo de ordenamiento no está permitido.
     */
    public PageRequest validateAndBuild(final Set<String> allowedSortFields) {
        if (sort != null && !sort.isEmpty()) {
            List<String> invalidSortFields = sort.parallelStream()
                    .map(s -> s.split(",")[0].trim())
                    .filter(field -> !allowedSortFields.contains(field))
                    .toList();
            if (!invalidSortFields.isEmpty()) {
                throw new BadRequestException("Invalid sort fields: " + String.join(", ", invalidSortFields));
            }
        }
        return toPageRequest();
    }
}
