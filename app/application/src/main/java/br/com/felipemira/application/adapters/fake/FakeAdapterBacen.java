package br.com.felipemira.application.adapters.fake;

import br.com.felipemira.application.core.ports.out.BacenPort;
import br.com.felipemira.application.core.domain.model.TransactionBacen;
import jakarta.inject.Named;

@Named
public class FakeAdapterBacen implements BacenPort {
    @Override
    public void postTransactionBacen(TransactionBacen transactionBacen) {
        System.out.println("Fake banco de dados -> postTransactionBacen(" + transactionBacen + ")");
    }
}
