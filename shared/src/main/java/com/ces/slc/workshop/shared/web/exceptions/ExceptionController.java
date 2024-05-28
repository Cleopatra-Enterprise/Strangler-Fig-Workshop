package com.ces.slc.workshop.shared.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.ServletException;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorModel handleValidationExceptions(MethodArgumentNotValidException exception) {
        ErrorModel errors = new ErrorModel("Validation failed");
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.addError(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorModel handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ErrorModel(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoResourceFoundException.class})
    public ErrorModel handleResourceNotFoundException(NoResourceFoundException exception) {
        ErrorModel model = new ErrorModel("Resource not found");
        model.addError("path", exception.getResourcePath());
        return model;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorModel handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ErrorModel(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServletException.class)
    public ErrorModel handleServletException(ServletException exception) {
        return handleException(exception.getCause());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorModel handleException(Throwable exception) {
        return new ErrorModel(exception.getMessage());
    }
}
