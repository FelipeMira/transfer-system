package br.com.felipemira.api.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundCustom extends Exception {

    private static final long serialVersionUID = 1L;

    public NotFoundCustom(final String message) {
        super(NOT_FOUND, message);
    }
}
