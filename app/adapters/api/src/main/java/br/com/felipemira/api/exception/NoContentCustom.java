package br.com.felipemira.api.exception;

import static org.springframework.http.HttpStatus.NO_CONTENT;

public class NoContentCustom extends Exception {

    private static final long serialVersionUID = 1L;

    public NoContentCustom(final String message) {
        super(NO_CONTENT, message);
    }

}
