package br.com.felipemira.bacen.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter@Setter
public class TransferenciaBacenRequest implements Serializable {

    private Integer idDebito;

    private Integer idCredito;

    private BigDecimal valor;
}