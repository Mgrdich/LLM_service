package com.llm_service.llm_service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record ValidationErrorResponse(Map<String, String> errors) {
    @JsonCreator
    public ValidationErrorResponse(@JsonProperty("errors") Map<String, String> errors) {
        this.errors = errors != null ? new HashMap<>(errors) : new HashMap<>();
    }

    public void addError(String field, String message) {
        errors.put(field, message);
    }
}
