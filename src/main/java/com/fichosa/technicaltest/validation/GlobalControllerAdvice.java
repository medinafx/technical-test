package com.fichosa.technicaltest.validation;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ValidationError handleValidationExceptions(InvalidFormatException e) {
        return new ValidationError("Invalid value '" + e.getValue().toString()
                + "' for field '" + e.getPath().get(0).getFieldName()
                + "'. Expected type: " + e.getTargetType().getSimpleName());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDefinitionException.class)
    public ValidationError handleInvalidDefinitionException(InvalidDefinitionException e) {
        return new ValidationError("Cannot construct instance of String[] for value ");
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ValidationError> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatus().value()).body(new ValidationError(e.getReason()));
    }
}