package com.fichosa.technicaltest.validation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationError {

    private String path;
    private String message;

    public ValidationError(String message) {
        this(null, message);
    }

    public ValidationError(List<String> messages) {
        this(null, null);
    }

    public ValidationError(String path, String messages) {
        this.path = path;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}