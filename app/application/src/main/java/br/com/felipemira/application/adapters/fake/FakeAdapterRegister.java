package br.com.felipemira.application.adapters.fake;

import br.com.felipemira.application.core.ports.out.RegisterPort;
import br.com.felipemira.application.core.domain.model.AccountHolder;
import jakarta.inject.Named;

import java.util.HashMap;
import java.util.Map;

// Responsavel por implementar a porta de saída (driven) de servicos de API falso.
// Sera gerenciado pelo IoC
@Named
public class FakeAdapterRegister implements RegisterPort {

    private final Map<Long, AccountHolder> registers = new HashMap<>();

    public FakeAdapterRegister() {
        addAccountHolder(new AccountHolder(1L, "Felipe Mira"));
        addAccountHolder(new AccountHolder(2L, "Joao Silva"));
        addAccountHolder(new AccountHolder(3L, "Maria Santos"));
        addAccountHolder(new AccountHolder(4L, "Jose Souza"));
    }

    private void addAccountHolder(AccountHolder accountHolder) {
        registers.put(accountHolder.getIdAccountHolder(), accountHolder);
    }

    @Override
    public AccountHolder getAccountHolder(Long id) {
        System.out.println("Fake api cadastro -> Correntista get(" + id + ")");
        return registers.get(id);
    }
}
