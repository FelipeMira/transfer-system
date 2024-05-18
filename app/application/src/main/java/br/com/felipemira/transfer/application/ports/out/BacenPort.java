package br.com.felipemira.transfer.application.ports.out;

import br.com.felipemira.transfer.application.domain.model.TransactionBacen;

// Responsavel por definir a porta de saída (driven) de servicos de API do BACEN.
public interface BacenPort {

    void postTransactionBacen(TransactionBacen transactionBacen);
}
