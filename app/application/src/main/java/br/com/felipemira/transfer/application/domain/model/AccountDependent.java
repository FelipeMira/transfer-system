package br.com.felipemira.transfer.application.domain.model;

import br.com.felipemira.transfer.application.domain.model.enums.FamilyMemberType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(setterPrefix = "with")
public class AccountDependent extends AbstractAccountMain {
	private FamilyMemberType familyMemberType;
}
