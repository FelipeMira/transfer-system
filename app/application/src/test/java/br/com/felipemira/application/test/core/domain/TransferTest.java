package br.com.felipemira.application.test.core.domain;

import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.domain.model.AccountHolder;
import br.com.felipemira.application.core.domain.model.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Transfer")
class TransferTest {

    private Transfer transfer;
    private Account debitAccount;
    private Account creditAccount;

    @BeforeEach
    void setUp() {
        debitAccount = new Account(1L, new BigDecimal(100), new AccountHolder(1L, "Debit Account Holder"), 1, null, new BigDecimal(100));
        creditAccount = new Account(2L, new BigDecimal(100), new AccountHolder(2L, "Credit Account Holder"), 1, null, new BigDecimal(100));
        transfer = new Transfer(debitAccount, creditAccount, new BigDecimal(50));
    }

    @Test
    @DisplayName("Deve definir a conta de débito")
    void shouldSetDebitAccount() {
        Account newDebitAccount = new Account(3L, new BigDecimal(200), new AccountHolder(3L, "New Debit Account Holder"), 1, null, new BigDecimal(200));
        transfer.setDebit(newDebitAccount);
        assertEquals(newDebitAccount, transfer.getDebit());
    }

    @Test
    @DisplayName("Deve definir a conta de crédito")
    void shouldSetCreditAccount() {
        Account newCreditAccount = new Account(4L, new BigDecimal(200), new AccountHolder(4L, "New Credit Account Holder"), 1, null, new BigDecimal(200));
        transfer.setCredit(newCreditAccount);
        assertEquals(newCreditAccount, transfer.getCredit());
    }

    @Test
    @DisplayName("Deve definir o valor da transferência")
    void shouldSetValue() {
        BigDecimal newValue = new BigDecimal(75);
        transfer.setValue(newValue);
        assertEquals(newValue, transfer.getValue());
    }
}
