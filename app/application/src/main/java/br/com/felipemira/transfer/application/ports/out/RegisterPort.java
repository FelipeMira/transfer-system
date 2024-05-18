package br.com.felipemira.transfer.application.ports.out;

import br.com.felipemira.transfer.application.domain.model.AccountHolder;

// Responsavel por definir a porta de saída (driven) de servicos de API de cadastro.
public interface RegisterPort {

    AccountHolder getAccountHolder(Long id);
}
