package br.com.felipemira.database.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HolderEntity {

    @Column(name = "correntista")
    private Integer idAccountHolder;
    @Transient
    private String name;
}
