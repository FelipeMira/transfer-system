package br.com.felipemira.transfer.application.ports.in;

import br.com.felipemira.transfer.application.domain.model.Account;
import jakarta.validation.Valid;

import static br.com.felipemira.transfer.validation.Validation.validate;


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
