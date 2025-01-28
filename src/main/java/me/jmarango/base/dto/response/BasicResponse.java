package me.jmarango.base.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BasicResponse {
    @Schema(example = "false", defaultValue = "false")
    private boolean success=false;
    private final String message;
}
