package br.com.felipemira.application.core.domain.model;

import br.com.felipemira.application.core.validation.annotation.CustomTransferValidation;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@CustomTransferValidation
@Getter@Setter
@AllArgsConstructor
public class Transfer {

    @NotNull(message = "deve ser informado")
    public Account debit;
    @NotNull(message = "deve ser informado")
    public Account credit;
    @NotNull(message = "deve ser informado")
    @DecimalMin(value = "0.01", message = "deve ser maior que zero")
    public BigDecimal value;
}
