package br.com.felipemira.web.out.feing.controller;

import br.com.felipemira.application.core.domain.model.AccountHolder;
import br.com.felipemira.application.core.ports.out.RegisterPort;
import br.com.felipemira.web.out.dto.response.RegisterResponse;
import br.com.felipemira.web.out.feing.clients.RegisterHolderAccountFeing;
import br.com.felipemira.web.out.mappers.AccountHolderMapper;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Named
public class AccountHolderController implements RegisterPort {

    private final RegisterHolderAccountFeing registerHolderAccountFeing;

    private final AccountHolderMapper accountHolderMapper = new AccountHolderMapper();

    @Inject
    public AccountHolderController(RegisterHolderAccountFeing registerHolderAccountFeing){
        this.registerHolderAccountFeing = registerHolderAccountFeing;
    }

    @Override
    @Cacheable( value = "accountHolder", key = "#idCorrentista")
    public AccountHolder getAccountHolder(Long idCorrentista) {

        Optional<RegisterResponse> optionalRegisterResponse = Optional.ofNullable(registerHolderAccountFeing.getRegister(idCorrentista));

        return optionalRegisterResponse.map(accountHolderMapper::mapToDomain).orElse(null);
    }
}
