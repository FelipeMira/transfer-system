package br.com.felipemira.application.core.ports.out;

import br.com.felipemira.application.core.domain.model.Account;

// Responsavel por definir a porta de saída (driven) de servicos de banco de dados.
public interface AccountPort {

    Account getAccount(Long accountNumber);
    void updateAccount(Account account);
}
