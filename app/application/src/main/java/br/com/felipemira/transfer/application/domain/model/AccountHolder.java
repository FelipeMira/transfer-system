package br.com.felipemira.transfer.application.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

// Responsavel por representar a entidade correntista e suas regras.
// Nao sera gerenciado pelo IoC e sim pelo repositorio.
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(setterPrefix = "with")
@EqualsAndHashCode(callSuper=true)
public class AccountHolder extends AbstractAccountMain {
	private String registry;

	public AccountHolder(Long idAccountHolder){
		setIdAccount(idAccountHolder);
	}

	public AccountHolder(Long idAccountHolder, String name){
		setIdAccount(idAccountHolder);
		setName(name);
	}
}
