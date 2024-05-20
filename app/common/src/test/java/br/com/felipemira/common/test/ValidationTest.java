package br.com.felipemira.common.test;

import br.com.felipemira.common.validation.Validation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationTest {

    @Test
    @DisplayName("Valid object does not throw exception")
    void validObjectDoesNotThrowException() {
        class ValidObject {
            @NotNull
            String notNullField = "not null";
        }

        assertDoesNotThrow(() -> Validation.validate(new ValidObject()));
    }

    @Test
    @DisplayName("Invalid object throws ConstraintViolationException")
    void invalidObjectThrowsConstraintViolationException() {
        class InvalidObject {
            @NotNull
            String nullField = null;
        }

        assertThrows(ConstraintViolationException.class, () -> Validation.validate(new InvalidObject()));
    }
}