package br.com.felipemira.application.core.ports.in;

import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.common.domain.pagination.AppPage;
import br.com.felipemira.common.domain.pagination.AppPageable;
import jakarta.validation.Valid;

import static br.com.felipemira.common.validation.Validation.validate;


public interface AccountInfoUseCase {

    Account getAccount(AccountCommand accountCommand);

    AppPage<Account> fetchAccounts(AccountPageableCommand accountPageableCommand);

    record AccountCommand(@Valid Account account) {
        public AccountCommand(
                Account account) {
            this.account = account;
            validate(this);
        }
    }

    record AccountPageableCommand(@Valid AppPageable pageable) {
        public AccountPageableCommand(
                AppPageable pageable) {
            this.pageable = pageable;
            validate(this);
        }
    }

}
