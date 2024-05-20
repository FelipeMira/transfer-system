package br.com.felipemira.web.in.domain.request.change;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Valor da transferencia",
            example = "10.00")
    private BigDecimal transferValue;

}
