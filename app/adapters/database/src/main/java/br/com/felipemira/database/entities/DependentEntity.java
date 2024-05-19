package br.com.felipemira.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder(setterPrefix = "with")
public class DependentEntity extends AbstractMainEntity {
	private String familyMemberType;
}
