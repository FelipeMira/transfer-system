package br.com.felipemira.transfer.system.validation.annotation;

import br.com.felipemira.transfer.system.domain.model.Transfer;
import br.com.felipemira.transfer.system.validation.constraint.CustomTransferValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CustomTransferValidator.class)
@Documented
public @interface CustomTransferValidation {

    String message() default """

            One or more violations found:\s
            """;

    Class<?>[] groups() default {};

    Class<? extends Transfer>[] payload() default {};

}
