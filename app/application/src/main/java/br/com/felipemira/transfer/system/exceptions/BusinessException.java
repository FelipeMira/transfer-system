package br.com.felipemira.transfer.system.exceptions;

// Responsavel por representar um erro de negocio de sistema.
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}