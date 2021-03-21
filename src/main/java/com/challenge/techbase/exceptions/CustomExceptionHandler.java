package com.challenge.techbase.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        final List<String> errorList = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .distinct()
                .collect(Collectors.toList());
        final ValidationExceptionResponse response =
                new ValidationExceptionResponse(HttpStatus.BAD_REQUEST.value(), errorList, LocalDateTime.now());
        log.warn("Validation errors : {} , Parameters : {}", errorList, exception.getBindingResult().getTarget());

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<RestExceptionResponse> handleRestException(RestException exception) {
        RestExceptionResponse response = new RestExceptionResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(response.getCode()).body(response);
    }
}
