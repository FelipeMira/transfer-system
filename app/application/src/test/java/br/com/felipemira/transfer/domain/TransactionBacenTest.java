package br.com.felipemira.transfer.domain;

import br.com.felipemira.transfer.application.domain.model.TransactionBacen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe TransactionBacen")
class TransactionBacenTest {

    private TransactionBacen transactionBacen;

    @BeforeEach
    void setUp() {
        transactionBacen = new TransactionBacen();
    }

    @Test
    @DisplayName("Deve definir o ID de débito")
    void shouldSetIdDebit() {
        Long expectedId = 1L;
        transactionBacen.setIdDebit(expectedId);
        assertEquals(expectedId, transactionBacen.getIdDebit());
    }

    @Test
    @DisplayName("Deve definir o ID de crédito")
    void shouldSetIdCredit() {
        Long expectedId = 1L;
        transactionBacen.setIdCredit(expectedId);
        assertEquals(expectedId, transactionBacen.getIdCredit());
    }

    @Test
    @DisplayName("Deve definir o valor")
    void shouldSetValue() {
        BigDecimal expectedValue = new BigDecimal(100);
        transactionBacen.setValue(expectedValue);
        assertEquals(expectedValue, transactionBacen.getValue());
    }
}
