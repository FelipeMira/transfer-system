package br.com.felipemira.application.core.validation.constraint;

import br.com.felipemira.application.core.domain.model.Transfer;
import br.com.felipemira.application.core.validation.annotation.CustomTransferValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class CustomTransferValidator implements ConstraintValidator<CustomTransferValidation, Transfer> {

    @Override
    public void initialize(CustomTransferValidation constraintAnnotation) {
        String message = constraintAnnotation.message();
        log.debug("Validator: {}", message);
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Transfer transfer, ConstraintValidatorContext context) {

        boolean pass = true;

        if (Objects.nonNull(transfer.getDebit()) && Objects.nonNull(transfer.getCredit()) && transfer.getDebit().getNumber().equals(transfer.getCredit().getNumber())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("nao deve ser igual a transfer.credit")
                    .addPropertyNode("debit")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate("nao deve ser igual a transfer.debit")
                    .addPropertyNode("credit")
                    .addConstraintViolation();
            pass = false;
        }

        return pass;
    }
}
