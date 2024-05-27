package com.ces.slc.workshop.modules.core.web.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorModel {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Map<String, String> errors = new HashMap<>();

    private final String message;
    private final LocalDateTime timestamp;

    public ErrorModel(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public void addError(String fieldName, String errorMessage) {
        errors.put(fieldName, errorMessage);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
