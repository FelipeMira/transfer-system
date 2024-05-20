package br.com.felipemira.cadastro.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Correntista {
    @Getter
    @Setter
    private Integer idCorrentista;
    @Getter@Setter
    private String nome;

    public Correntista(Integer idCorrentista){
        this.idCorrentista = idCorrentista;
    }
}
