package me.jmarango.base.dto.response.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageInfo<T> {
    private List<T> elements;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long totalElements;

    private int actualPage;
    private int totalPages;
}
