package br.com.felipemira.application.core.domain.service;

import br.com.felipemira.application.core.ports.out.AccountPort;
import br.com.felipemira.application.core.ports.out.RegisterPort;
import br.com.felipemira.common.annotations.UseCase;
import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.ports.in.AccountInfoUseCase;
import jakarta.inject.Inject;

import static br.com.felipemira.application.core.exceptions.Error.*;
import static br.com.felipemira.application.core.exceptions.Error.accountHolderNonexistent;
import static java.util.Objects.isNull;

@UseCase
public class AccountInfoService implements AccountInfoUseCase {

    private final AccountPort accountPort;

    private final RegisterPort registerPort;

    @Inject
    public AccountInfoService(AccountPort accountPort, RegisterPort registerPort) {
        this.accountPort = accountPort;
        this.registerPort = registerPort;
    }

    @Override
    public Account getAccount(AccountCommand accountCommand) {

        var account = accountPort.getAccount(accountCommand.account().getNumber());

        if(isNull(account)){
            nonexistent(String.valueOf(accountCommand.account().getNumber()));
        }

        account.checkActiveAccount();

        var accountHolder = registerPort.getAccountHolder(account.getAccountHolder().getIdAccountHolder());

        if(isNull(accountHolder)){
            accountHolderNonexistent(account.getAccountHolder().getIdAccountHolder());
        }

        account.setAccountHolder(accountHolder);

        return account;
    }
}
