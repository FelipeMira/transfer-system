package br.com.felipemira.transfer.adapters;

import br.com.felipemira.transfer.common.FakePort;
import br.com.felipemira.transfer.system.domain.model.TransactionBacen;
import br.com.felipemira.transfer.system.ports.out.BacenPort;

@FakePort
public class FakeAdapterBacen implements BacenPort {
    @Override
    public void postTransactionBacen(TransactionBacen transactionBacen) {
        System.out.println("Fake banco de dados -> postTransactionBacen(" + transactionBacen + ")");
    }
}
