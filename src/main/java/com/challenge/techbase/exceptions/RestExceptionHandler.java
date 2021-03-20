package com.challenge.techbase.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RestException.class })
    public ResponseEntity<RestExceptionResponse> handleRestException(RestException exception) {
        RestExceptionResponse response = new RestExceptionResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(response.getCode()).body(response);
    }
}
