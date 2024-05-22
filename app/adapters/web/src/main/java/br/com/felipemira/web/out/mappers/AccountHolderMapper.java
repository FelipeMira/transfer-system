package br.com.felipemira.web.out.mappers;

import br.com.felipemira.application.core.domain.model.AccountHolder;
import br.com.felipemira.web.out.dto.response.RegisterResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class AccountHolderMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public AccountHolderMapper() {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper.typeMap(RegisterResponse.class, AccountHolder.class).addMappings(mapper -> {
            mapper.map(RegisterResponse::getNome, AccountHolder::setName);
            mapper.map(RegisterResponse::getIdCorrentista, AccountHolder::setIdAccountHolder);
        });
        this.modelMapper.typeMap(AccountHolder.class, RegisterResponse.class).addMappings(mapper -> {
            mapper.map(AccountHolder::getName, RegisterResponse::setNome);
            mapper.map(AccountHolder::getIdAccountHolder, RegisterResponse::setIdCorrentista);
        });
    }

    public AccountHolder mapToDomain(RegisterResponse registerResponse) {
        return modelMapper.map(registerResponse, AccountHolder.class);
    }

    public RegisterResponse mapToDto(AccountHolder accountHolder) {
        return modelMapper.map(accountHolder, RegisterResponse.class);
    }
}
