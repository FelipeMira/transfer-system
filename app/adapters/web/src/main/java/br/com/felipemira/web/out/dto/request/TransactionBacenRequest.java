package br.com.felipemira.web.out.dto.request;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionBacenRequest implements Serializable {

        private Integer idDebito;
        private Integer idCredito;
        private BigDecimal valor;
}
