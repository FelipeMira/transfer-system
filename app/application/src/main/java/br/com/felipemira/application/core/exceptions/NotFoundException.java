package br.com.felipemira.application.core.exceptions;

// Responsavel por representar um erro de negocio de sistema.
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}