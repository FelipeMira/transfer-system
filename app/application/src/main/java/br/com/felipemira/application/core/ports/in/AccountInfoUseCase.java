package br.com.felipemira.application.core.ports.in;

import br.com.felipemira.application.core.domain.model.Account;
import jakarta.validation.Valid;

import static br.com.felipemira.common.validation.Validation.validate;


public interface AccountInfoUseCase {

    Account getAccount(AccountCommand accountCommand);

    record AccountCommand(@Valid Account account) {

        public AccountCommand(
                Account account) {
            this.account = account;
            validate(this);
        }

    }

}
