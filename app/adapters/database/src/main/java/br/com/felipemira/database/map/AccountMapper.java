package br.com.felipemira.database.map;

import br.com.felipemira.database.entities.AccountEntity;
import br.com.felipemira.transfer.application.domain.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AccountHolderMapper.class)
public interface AccountMapper {
	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

	Account toDomain(AccountEntity account);
	AccountEntity toEntity(Account account);
}
