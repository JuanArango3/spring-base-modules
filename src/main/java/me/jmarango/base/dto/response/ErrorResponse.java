package me.jmarango.base.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    public ErrorResponse(String message) {
        this.message = message;
    }

    private String message;
    private Map<String, String> errors;
}
