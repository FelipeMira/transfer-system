package br.com.felipemira.application.test.core.domain;

import br.com.felipemira.application.core.domain.model.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe AccountHolder")
class AccountHolderTest {

    private AccountHolder accountHolder;

    @BeforeEach
    void setUp() {
        accountHolder = new AccountHolder();
    }

    @Test
    @DisplayName("Deve definir o ID do titular da conta")
    void shouldSetIdAccountHolder() {
        Long expectedId = 1L;
        accountHolder.setIdAccountHolder(expectedId);
        assertEquals(expectedId, accountHolder.getIdAccountHolder());
    }

    @Test
    @DisplayName("Deve definir o nome")
    void shouldSetName() {
        String expectedName = "Test Name";
        accountHolder.setName(expectedName);
        assertEquals(expectedName, accountHolder.getName());
    }

    @Test
    @DisplayName("Deve criar um titular da conta com ID")
    void shouldCreateAccountHolderWithId() {
        Long expectedId = 1L;
        AccountHolder newAccountHolder = new AccountHolder(expectedId);
        assertEquals(expectedId, newAccountHolder.getIdAccountHolder());
    }
}
