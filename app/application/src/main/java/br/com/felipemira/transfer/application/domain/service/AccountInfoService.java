package br.com.felipemira.transfer.application.domain.service;

import br.com.felipemira.transfer.common.UseCase;
import br.com.felipemira.transfer.application.domain.model.Account;
import br.com.felipemira.transfer.application.ports.in.AccountInfoUseCase;
import br.com.felipemira.transfer.application.ports.out.AccountPort;
import br.com.felipemira.transfer.application.ports.out.RegisterPort;
import jakarta.inject.Inject;

import static br.com.felipemira.transfer.application.exceptions.Error.*;
import static br.com.felipemira.transfer.application.exceptions.Error.accountHolderNonexistent;
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

        var accountHolder = registerPort.getAccountHolder(account.getAccountMain().getIdAccount());

        if(isNull(accountHolder)){
            accountHolderNonexistent(account.getAccountMain().getIdAccount());
        }

        account.setAccountMain(accountHolder);

        return account;
    }
}
