package br.com.felipemira.transfer.application.validation.annotation;

import br.com.felipemira.transfer.application.validation.constraint.CustomTransferValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = CustomTransferValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomTransferValidation {
    String message() default "Invalid transfer";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
