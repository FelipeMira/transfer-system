package br.com.felipemira.database.entities;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder(setterPrefix = "with")
public class HolderEntity extends AbstractMainEntity {
	private String registry;
}
