package com.challenge.techbase.exceptions;

public class RestException extends RuntimeException {

    public RestException() {
    }

    public RestException(String message) {
        super(message);
    }
}
