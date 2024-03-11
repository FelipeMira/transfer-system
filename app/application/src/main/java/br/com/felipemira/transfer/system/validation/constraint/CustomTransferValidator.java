package br.com.felipemira.transfer.system.validation.constraint;

import br.com.felipemira.transfer.system.domain.model.Transfer;
import br.com.felipemira.transfer.system.validation.annotation.CustomTransferValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CustomTransferValidator implements ConstraintValidator<CustomTransferValidation, Transfer> {

    private String message;

    @Override
    public void initialize(CustomTransferValidation constraintAnnotation) {
        this.message = constraintAnnotation.message();
        log.debug("Validator: {}", message);
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Transfer transfer, ConstraintValidatorContext constraintValidatorContext) {
        List<String> errorMessages = new ArrayList<>();

        if(Objects.isNull(transfer.getDebit())){
            errorMessages.add("Debit account is required");
        }
        if(Objects.isNull(transfer.getCredit())){
            errorMessages.add("Credit account is required");
        }
        if(Objects.isNull(transfer.getValue())){
            errorMessages.add("Value is required");
        }
        if(Objects.nonNull(transfer.getDebit()) && Objects.nonNull(transfer.getCredit()) && transfer.getDebit().equals(transfer.getCredit())){
            errorMessages.add("Debit and credit accounts must be different");
        }
        if(Objects.nonNull(transfer.getValue()) && (transfer.getValue()).compareTo(new BigDecimal(0)) <= 0){
            errorMessages.add("Insufficient value");
        }

        if (!errorMessages.isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            String finalMessage = this.message + String.join("\n", errorMessages);
            constraintValidatorContext.buildConstraintViolationWithTemplate(finalMessage).addConstraintViolation();
            return false;
        }

        return true;
    }
}
