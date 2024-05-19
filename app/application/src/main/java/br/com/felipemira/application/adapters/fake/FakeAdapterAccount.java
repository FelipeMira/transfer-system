package br.com.felipemira.application.adapters.fake;

import br.com.felipemira.common.annotations.FakePort;
import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.domain.model.AccountHolder;
import br.com.felipemira.application.core.ports.out.AccountPort;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

// Responsavel por implementar a porta de saída (driven) de servicos de banco de dados falso.
// Sera gerenciado pelo IoC
@FakePort
public class FakeAdapterAccount implements AccountPort {

    private final Map<Long, Account> accounts = new HashMap<>();

    public FakeAdapterAccount() {
        addAccount(new Account(1L, new BigDecimal(1000L), new AccountHolder(1L), 1, null, new BigDecimal(1000)));
        addAccount(new Account(2L, new BigDecimal(1000L), new AccountHolder(2L), 1, null, new BigDecimal(1000)));
        addAccount(new Account(3L, new BigDecimal(1000L), new AccountHolder(3L), 1, null, new BigDecimal(1000)));
        addAccount(new Account(4L, new BigDecimal(1000L), new AccountHolder(4L), 1, null, new BigDecimal(1000)));
        addAccount(new Account(5L, new BigDecimal(1000L), new AccountHolder(5L), 1, null, new BigDecimal(1000)));
        addAccount(new Account(6L, new BigDecimal(50L), new AccountHolder(1L), 1, null, new BigDecimal(1000)));
        addAccount(new Account(7L, new BigDecimal(50L), new AccountHolder(2L), 1, null, new BigDecimal(1000)));
        //inativa
        addAccount(new Account(8L, new BigDecimal(50L), new AccountHolder(2L), 0, null, new BigDecimal(1000)));
    }

    private void addAccount(Account account) {
        accounts.put(account.getNumber(), account);
    }

    @Override
    public Account getAccount(Long accountNumber) {
        System.out.println("Fake banco de dados -> Conta get("+ accountNumber +")");
        return accounts.get(accountNumber);
    }

    @Override
    public void updateAccount(Account account) {
        System.out.println("Fake banco de dados -> alterar("+ account.getNumber() +")");
        var ct = accounts.get(account.getNumber());
        if (!isNull(ct)) {
            accounts.put(account.getNumber(), account);
        }
    }
}
