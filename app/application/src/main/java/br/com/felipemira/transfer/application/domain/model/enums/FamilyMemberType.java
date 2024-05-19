package br.com.felipemira.transfer.application.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.MissingFormatArgumentException;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum FamilyMemberType {

	COUSIN("PRIMA"),
	SISTER("IRMA");

	private final String description;

	public static FamilyMemberType fromDescription(String description) {
		for (FamilyMemberType familyMemberType : values()) {
			if (description.equalsIgnoreCase(familyMemberType.name())) {
				return familyMemberType;
			}
		}

		System.out.println(String.format("Not exists family member type %s", description));

		throw new MissingFormatArgumentException(String.format("Tipo parentesco %s não encontrado."));
	}
}
