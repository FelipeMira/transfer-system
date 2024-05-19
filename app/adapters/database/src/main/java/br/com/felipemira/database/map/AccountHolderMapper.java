package br.com.felipemira.database.map;

import br.com.felipemira.database.entities.AbstractMainEntity;
import br.com.felipemira.database.entities.DependentEntity;
import br.com.felipemira.database.entities.HolderEntity;
import br.com.felipemira.transfer.application.domain.model.AbstractAccountMain;
import br.com.felipemira.transfer.application.domain.model.AccountDependent;
import br.com.felipemira.transfer.application.domain.model.AccountHolder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.SubclassMappings;

import static org.mapstruct.SubclassExhaustiveStrategy.RUNTIME_EXCEPTION;

@Mapper(subclassExhaustiveStrategy = RUNTIME_EXCEPTION)
public interface AccountHolderMapper {

	@SubclassMappings({
			@SubclassMapping(source = HolderEntity.class, target = AccountHolder.class),
			@SubclassMapping(source = DependentEntity.class, target = AccountDependent.class)
	})
	AbstractAccountMain toDomain(AbstractMainEntity mainEntity);

	@InheritInverseConfiguration
	AbstractMainEntity toEntity(AbstractAccountMain accountMain);
}
