package br.com.felipemira.application.core.service;

import br.com.felipemira.application.core.domain.model.AccountHolder;
import br.com.felipemira.application.core.exceptions.MessagesException;
import br.com.felipemira.application.core.ports.out.AccountPort;
import br.com.felipemira.application.core.ports.out.RegisterPort;
import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.ports.in.AccountInfoUseCase;
import br.com.felipemira.common.domain.pagination.AppPage;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import static br.com.felipemira.application.core.exceptions.MessagesException.*;
import static br.com.felipemira.application.core.exceptions.MessagesException.accountHolderNonexistent;
import static java.util.Objects.isNull;

@Named
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
            accountNonexistent(accountCommand.account().getNumber());
        }

        account.checkActiveAccount();

        var accountHolder = registerPort.getAccountHolder(account.getAccountHolder().getIdAccountHolder());

        if(isNull(accountHolder)){
            accountHolderNonexistent(account.getAccountHolder().getIdAccountHolder());
        }

        account.setAccountHolder(accountHolder);

        return account;
    }

    @Override
    public AppPage<Account> fetchAccounts(AccountPageableCommand accountPageableCommand) {
        AppPage<Account> accountsPage = accountPort.fetchAccounts(accountPageableCommand.pageable());

        // Atualizar cada AccountHolder antes de retornar a lista de contas
        accountsPage.getContent().forEach(account -> {
            AccountHolder updatedAccountHolder = registerPort.getAccountHolder(account.getAccountHolder().getIdAccountHolder());
            if (updatedAccountHolder != null) {
                account.setAccountHolder(updatedAccountHolder);
            }
        });

        return accountsPage;
    }
}
