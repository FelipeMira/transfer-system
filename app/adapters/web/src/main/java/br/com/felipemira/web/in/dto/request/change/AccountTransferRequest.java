package br.com.felipemira.web.in.dto.request.change;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferRequest implements Serializable {

    @Schema(description = "Conta Debito",
            example = "1")
    @NotNull
    @Min(1)
    private Integer accountDebit;

    @Schema(description = "Conta Credito",
            example = "1")
    @NotNull
    @Min(1)
    private Integer accountCredit;

    @Schema(description = "Valor da transferencia",
            example = "10.00")
    @NotNull
    @DecimalMin(value = "0.01", message = "The transfer value must be greater than 0.01")
    private BigDecimal transferValue;

}
