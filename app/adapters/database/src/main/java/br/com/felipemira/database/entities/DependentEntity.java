package br.com.felipemira.database.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder(setterPrefix = "with")
@EqualsAndHashCode(callSuper = true)
public class DependentEntity extends AbstractMainEntity {
	private String familyMemberType;
}
