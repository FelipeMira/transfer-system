package br.com.felipemira.transfer.system.ports.out;

import br.com.felipemira.transfer.system.domain.model.Account;

// Responsavel por definir a porta de saída (driven) de servicos de banco de dados.
public interface AccountPort {

    Account getAccount(Long accountNumber);
    void updateAccount(Account account);
}
