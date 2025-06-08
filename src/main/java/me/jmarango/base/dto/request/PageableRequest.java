package me.jmarango.base.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

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

    private List<EnderSort> sort;

    @Data
    @AllArgsConstructor
    public static class EnderSort {
        @Schema(description = "Campo por el cual ordenar")
        private String field;

        @Schema(defaultValue = "ASC", description = "Dirección de ordenamiento: ASC o DESC")
        private Direction direction;
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(page, size, Sort.by(sort.parallelStream().map(esort -> new Sort.Order(
                esort.getDirection() != null ? esort.getDirection() : Direction.ASC,
                esort.getField()
        )).toList()));
    }
}
