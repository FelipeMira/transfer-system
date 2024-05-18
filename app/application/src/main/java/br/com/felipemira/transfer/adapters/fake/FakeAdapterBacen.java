package br.com.felipemira.transfer.adapters.fake;

import br.com.felipemira.transfer.common.FakePort;
import br.com.felipemira.transfer.application.domain.model.TransactionBacen;
import br.com.felipemira.transfer.application.ports.out.BacenPort;

@FakePort
public class FakeAdapterBacen implements BacenPort {
    @Override
    public void postTransactionBacen(TransactionBacen transactionBacen) {
        System.out.println("Fake banco de dados -> postTransactionBacen(" + transactionBacen + ")");
    }
}
