package br.com.felipemira.transfer.application.domain.model;

import lombok.*;

import java.math.BigDecimal;

// Responsavel por representar a entidade conta e suas regras.
// Nao sera gerenciado pelo IoC e sim pelo repositorio.
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionBacen {
    @Getter@Setter
    private Long idDebit;
    @Getter@Setter
    private Long idCredit;
    @Getter@Setter
    private BigDecimal value;

}
