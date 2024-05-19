package br.com.felipemira.database.map;

import br.com.felipemira.database.entities.AccountEntity;
import br.com.felipemira.transfer.application.domain.model.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AccountHolderMapper.class)
public interface AccountMapper {
	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

	@Mapping(source = "mainEntity", target = "accountMain")
	Account toDomain(AccountEntity accountEntity);

	@InheritInverseConfiguration
	AccountEntity toEntity(Account account);
}
