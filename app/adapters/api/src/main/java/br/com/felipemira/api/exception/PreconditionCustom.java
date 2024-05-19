package br.com.felipemira.api.exception;

import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

public class PreconditionCustom extends Exception {

    private static final long serialVersionUID = 1L;

    public PreconditionCustom(final String message) {
        super(PRECONDITION_FAILED, message);
    }
}
