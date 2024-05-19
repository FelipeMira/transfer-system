package br.com.felipemira.application.core.exceptions;

// Responsavel por representar um erro de negocio de sistema.
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}