package me.jmarango.base.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortDTO {
    @Schema(description = "Campo por el que ordenar", example = "createdAt")
    private String property;

    @Schema(description = "Dirección de ordenamiento", example = "ASC")
    private Sort.Direction direction = Sort.Direction.ASC;

    public Sort.Order toSortOrder() {
        return new Sort.Order(direction, property);
    }
}
