package br.com.felipemira.database.map;

import br.com.felipemira.database.entities.HolderEntity;
import br.com.felipemira.transfer.application.domain.model.AccountHolder;
import org.mapstruct.Mapper;

@Mapper
public interface AccountHolderMapper {
	AccountHolder toDomain(HolderEntity holderEntity);
	HolderEntity toEntity(AccountHolder accountHolder);
}
