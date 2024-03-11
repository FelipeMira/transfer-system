package br.com.felipemira.transfer.system.domain.model;

import br.com.felipemira.transfer.system.validation.annotation.CustomTransferValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@CustomTransferValidation
@Getter@Setter
@AllArgsConstructor
public class Transfer {

    public Account debit;
    public Account credit;
    public BigDecimal value;
}
