package br.com.felipemira.transfer.application.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder(setterPrefix = "with")
public abstract class AbstractAccountMain {
	private Long idAccount;
	private String name;
}
