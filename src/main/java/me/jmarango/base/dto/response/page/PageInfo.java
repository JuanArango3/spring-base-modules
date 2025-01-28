package me.jmarango.base.dto.response.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {
    private List<T> elements;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalElements;

    private int actualPage;
    private int totalPages;
}
