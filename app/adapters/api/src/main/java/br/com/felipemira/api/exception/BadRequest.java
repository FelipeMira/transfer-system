package br.com.felipemira.api.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequest extends Exception {

    private static final long serialVersionUID = 1L;

    public BadRequest(final String message) {
        super(BAD_REQUEST, message);
    }
}

