package br.com.felipemira.api.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class Unauthorized extends Exception {

    private static final long serialVersionUID = 1L;

    public Unauthorized(final String message) {
        super(UNAUTHORIZED, message);
    }

}
