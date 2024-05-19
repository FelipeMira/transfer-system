package br.com.felipemira.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder(setterPrefix = "with")
@NoArgsConstructor
@ToString
public abstract class AbstractMainEntity {
	@Column(name = "correntista")
	private Integer idAccount;
	@Transient
	private String name;
}
