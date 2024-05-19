package br.com.felipemira.application.core.ports.out;

import br.com.felipemira.application.core.domain.model.TransactionBacen;

// Responsavel por definir a porta de saída (driven) de servicos de API do BACEN.
public interface BacenPort {

    void postTransactionBacen(TransactionBacen transactionBacen);
}
