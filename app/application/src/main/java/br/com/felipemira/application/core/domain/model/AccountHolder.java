package br.com.felipemira.application.core.domain.model;

import lombok.*;

// Responsavel por representar a entidade correntista e suas regras.
// Nao sera gerenciado pelo IoC e sim pelo repositorio.
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountHolder {
    @Getter@Setter
    private Long idAccountHolder;
    @Getter@Setter
    private String name;

    public AccountHolder(Long idAccountHolder){
        this.idAccountHolder = idAccountHolder;
    }
}
