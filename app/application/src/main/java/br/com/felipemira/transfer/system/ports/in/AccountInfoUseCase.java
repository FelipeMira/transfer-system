package br.com.felipemira.transfer.system.ports.in;

import br.com.felipemira.transfer.system.domain.model.Account;
import br.com.felipemira.transfer.system.domain.model.Transfer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

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
