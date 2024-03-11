package br.com.felipemira.transfer.domain;

import br.com.felipemira.transfer.system.domain.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Account")
class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    @DisplayName("Deve lancar excecao quando a conta esta inativa")
    void shouldThrowExceptionWhenAccountIsInactive() {
        account.setActive(0);
        assertThrows(RuntimeException.class, account::checkActiveAccount);
    }

    @Test
    @DisplayName("Nao deve lancar excecao quando a conta esta ativa")
    void shouldNotThrowExceptionWhenAccountIsActive() {
        account.setActive(1);
        assertDoesNotThrow(account::checkActiveAccount);
    }

    @Test
    @DisplayName("Deve atualizar a data e o limite quando a data e anterior a hoje")
    void shouldUpdateDateAndLimitWhenDateIsBeforeNow() {
        account.setDate(LocalDate.now().minusDays(1));
        account.dateVerify();
        assertEquals(LocalDate.now(), account.getDate());
        assertEquals(new BigDecimal(1000), account.getDailyLimit());
    }

    @Test
    @DisplayName("Deve aumentar o saldo quando o valor de credito e positivo")
    void shouldIncreaseBalanceWhenCreditValueIsPositive() {
        Account account = new Account();
        BigDecimal initialBalance = account.getBalance();
        BigDecimal creditValue = BigDecimal.valueOf(100);
        account.creditValue(creditValue);
        assertEquals(initialBalance.add(creditValue), account.getBalance());
    }

    @Test
    @DisplayName("Deve lancar excecao quando o valor de credito e negativo")
    void shouldThrowExceptionWhenCreditValueIsNegative() {
        Account account = new Account();
        assertThrows(RuntimeException.class, () -> account.creditValue(BigDecimal.valueOf(-1)));
    }

    @Test
    @DisplayName("Deve diminuir o saldo e o limite quando o valor de debito e menor que o saldo e o limite")
    void shouldDecreaseBalanceAndLimitWhenDebitValueIsLessThanBalanceAndLimit() {
        BigDecimal initialBalance = new BigDecimal(1000);
        BigDecimal initialLimit = new BigDecimal(1000);
        BigDecimal debitValue = new BigDecimal(100);
        account.setBalance(initialBalance);
        account.setDailyLimit(initialLimit);
        account.debitValue(debitValue);
        assertEquals(initialBalance.subtract(debitValue), account.getBalance());
        assertEquals(initialLimit.subtract(debitValue), account.getDailyLimit());
    }

    @Test
    @DisplayName("Deve lancar excecao quando o valor de debito e maior que o saldo")
    void shouldThrowExceptionWhenDebitValueIsGreaterThanBalance() {
        BigDecimal initialBalance = new BigDecimal(100);
        BigDecimal debitValue = new BigDecimal(200);
        account.setBalance(initialBalance);
        assertThrows(RuntimeException.class, () -> account.debitValue(debitValue));
    }

    @Test
    @DisplayName("Deve lancar excecao quando o valor de debito e maior que o limite")
    void shouldThrowExceptionWhenDebitValueIsGreaterThanLimit() {
        BigDecimal initialLimit = new BigDecimal(100);
        BigDecimal debitValue = new BigDecimal(200);
        account.setDailyLimit(initialLimit);
        assertThrows(RuntimeException.class, () -> account.debitValue(debitValue));
    }

    @Test
    @DisplayName("Deve lancar uma excecao quando o valor do credito e nulo")
    void shouldThrowExceptionWhenCreditValueIsNull() {
        Account account = new Account();
        assertThrows(RuntimeException.class, () -> account.creditValue(null));
    }

    @Test
    @DisplayName("Deve lancar uma excecao quando o valor do credito e zero")
    void shouldThrowExceptionWhenCreditValueIsZero() {
        Account account = new Account();
        assertThrows(RuntimeException.class, () -> account.creditValue(BigDecimal.ZERO));
    }

    @Test
    @DisplayName("Deve diminuir o saldo e o limite quando o valor do debito e menor que o saldo e o limite")
    void shouldDecreaseBalanceAndLimitWhenDebitValueIsPositiveAndLessThanBalanceAndLimit() {
        BigDecimal initialBalance = new BigDecimal(1000);
        BigDecimal initialLimit = new BigDecimal(1000);
        BigDecimal debitValue = new BigDecimal(100);
        account.setBalance(initialBalance);
        account.setDailyLimit(initialLimit);
        account.debitValue(debitValue);
        assertEquals(initialBalance.subtract(debitValue), account.getBalance());
        assertEquals(initialLimit.subtract(debitValue), account.getDailyLimit());
    }

    @Test
    @DisplayName("Deve lancar uma excecao quando o valor do debito e nulo")
    void shouldThrowExceptionWhenDebitValueIsNull() {
        assertThrows(RuntimeException.class, () -> account.debitValue(null));
    }

    @Test
    @DisplayName("Deve lancar uma excecao quando o valor do debito e zero")
    void shouldThrowExceptionWhenDebitValueIsZero() {
        assertThrows(RuntimeException.class, () -> account.debitValue(BigDecimal.ZERO));
    }

    @Test
    @DisplayName("Deve lancar uma excecao quando o valor do debito e maior que o limite diario")
    void shouldThrowExceptionWhenDebitValueIsGreaterThanDailyLimit() {
        BigDecimal initialLimit = new BigDecimal(100);
        BigDecimal debitValue = new BigDecimal(200);
        account.setDailyLimit(initialLimit);
        assertThrows(RuntimeException.class, () -> account.debitValue(debitValue));
    }
}
