package br.com.felipemira.application.core.ports.out;

import br.com.felipemira.application.core.domain.model.AccountHolder;

// Responsavel por definir a porta de saída (driven) de servicos de API de cadastro.
public interface RegisterPort {

    AccountHolder getAccountHolder(Long id);
}
