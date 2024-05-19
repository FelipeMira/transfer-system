package br.com.felipemira.api.exception;

import org.springframework.http.HttpStatus;

public class CustomRuntimeException extends Exception {

    private static final long serialVersionUID = 1L;

    public CustomRuntimeException(HttpStatus status, String message) {
        super(status, message);
    }
}

