package br.com.felipemira.transfer.system.ports.out;

import br.com.felipemira.transfer.system.domain.model.TransactionBacen;

// Responsavel por definir a porta de saída (driven) de servicos de API do BACEN.
public interface BacenPort {

    void postTransactionBacen(TransactionBacen transactionBacen);
}
