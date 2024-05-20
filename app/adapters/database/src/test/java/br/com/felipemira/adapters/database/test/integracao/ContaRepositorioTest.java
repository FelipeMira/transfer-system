package br.com.felipemira.adapters.database.test.integracao;

import br.com.felipemira.application.core.domain.model.AccountHolder;
import br.com.felipemira.application.core.exceptions.BusinessException;
import br.com.felipemira.application.core.ports.out.AccountPort;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

//@Rollback(false)
@Transactional
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Servico de banco de dados - Conta")
@ContextConfiguration(classes = Config.class)
public class ContaRepositorioTest {
    @Inject
    AccountPort rep;

    // negativos
    @Test
    @DisplayName("pesquisa conta com numero nulo")
    void test1() {
        try {
            var conta = rep.getAccount(null);
            assertNull(conta, "Conta deve ser nula");
        } catch (BusinessException e) {
            fail("Deve carregar uma conta nula.");
        }
    }
    @Test
    @DisplayName("pesquisa conta com numero inexistente")
    void teste2() {
        try {
            var conta = rep.getAccount(8547L);
            assertNull(conta, "Conta deve ser nula");
        } catch (BusinessException e) {
            fail("Deve carregar uma conta nula.");
        }
    }
    // positivo
    @Test
    @DisplayName("pesquisa conta com numero existente")
    void teste3() {
        try {
            var conta = rep.getAccount(1L);
            assertNotNull(conta, "Conta deve estar preenchida");
            System.out.println(conta);
        } catch (BusinessException e) {
            fail("Deve carregar uma conta.");
        }
    }
    // negativos
    @Test
    @DisplayName("alterar conta como nulo")
    void teste4() {
        try {
            rep.updateAccount(null);
            fail("Nao deve alterar conta nula");
        } catch (BusinessException e) {
            assertEquals("Conta e obrigatorio.", e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    // positivo
    @Test
    @DisplayName("alterar conta com sucesso")
    void teste5() {
        try {
            var c1 = rep.getAccount(1L);
            c1.setBalance(new BigDecimal("100.00"));
            c1.setAccountHolder(new AccountHolder(2L));
            rep.updateAccount(c1);
            var c2 = rep.getAccount(2L);
            assertEquals(c1.getBalance(), c2.getBalance(), "Deve bater o saldo");
            assertEquals(c1.getAccountHolder().getIdAccountHolder(), c2.getAccountHolder().getIdAccountHolder(), "Deve bater o correntista");
        } catch (BusinessException e) {
            fail("Deve alterar conta ");
        }
    }
}
