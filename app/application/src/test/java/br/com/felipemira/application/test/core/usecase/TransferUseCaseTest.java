package br.com.felipemira.application.test.core.usecase;

import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.domain.model.AccountHolder;
import br.com.felipemira.application.core.domain.model.Transfer;
import br.com.felipemira.application.core.exceptions.BusinessException;
import br.com.felipemira.application.core.ports.in.TransferUseCase;
import br.com.felipemira.application.test.core.usecase.builds.BuildOne;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Caso de Uso - Servico de Transferencia")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BuildOne.class)
public class TransferUseCaseTest {

    Long accountNonexistent = 40L;
    BigDecimal hundred = new BigDecimal(100);
    BigDecimal fifty = new BigDecimal(50);
    @Autowired
    TransferUseCase port;

    // negativos get conta
    @Test
    @DisplayName("Transferir com conta de debito, credito e valor nulo")
    void transferWithDebitCreditNullValue() {
        try {
            new TransferUseCase.TransferCommand(new Transfer(null, null, null));
            fail("Deve carregar uma conta nula.");
        } catch (ConstraintViolationException e) {
            String errorMessage = e.getMessage();
            assertTrue(errorMessage.contains("transfer.credit: deve ser informado"));
            assertTrue(errorMessage.contains("transfer.debit: deve ser informado"));
            assertTrue(errorMessage.contains("transfer.value: deve ser informado"));
        }
    }

    @Test
    @DisplayName("Transferir com conta de debito nula")
    void transferWithNullDebit() {
        try {
            Account account = new Account(1L, fifty, new AccountHolder(1L, "maria"), 1, null, hundred);
            new TransferUseCase.TransferCommand(new Transfer(null, account, hundred));
            fail("Deve carregar uma conta de debito nula.");
        } catch (ConstraintViolationException e) {
            assertEquals("""
                    transfer.debit: deve ser informado""", e.getMessage());
        }
    }

    @Test
    @DisplayName("Transferir com conta de credito nula")
    void transferWithNullCredit() {
        try {
            Account account = new Account(2L, fifty, new AccountHolder(2L, "Joao"), 1, null, hundred);
            new TransferUseCase.TransferCommand(new Transfer(account, null, fifty));
            fail("Deve carregar uma conta de credito nula.");
        } catch (ConstraintViolationException e) {
            assertEquals("""
                    transfer.credit: deve ser informado""", e.getMessage());
        }
    }

    @Test
    @DisplayName("Transferir com valor nulo")
    void transferWithNullValue() {
        try {
            Account accountDebit = new Account(1L, fifty, new AccountHolder(1L, "Maria"), 1, null, hundred);
            Account accountCredit = new Account(2L, fifty, new AccountHolder(2L, "Joao"), 1, null, hundred);
            new TransferUseCase.TransferCommand(new Transfer(accountDebit, accountCredit, null));
            fail("Deve carregar um valor nulo.");
        } catch (ConstraintViolationException e) {
            assertEquals("""
                    transfer.value: deve ser informado""", e.getMessage());
        }
    }

    @Test
    @DisplayName("Transferir com conta de debito, credito iguais")
    void transferWithDebitCreditEquals() {
        Account accountDebit = new Account(1L, fifty, new AccountHolder(1L, "Maria"), 1, null, hundred);
        Transfer transfer = new Transfer(accountDebit, accountDebit, fifty);
       var myThrow =  assertThrows(ConstraintViolationException.class, () -> port.transfer(new TransferUseCase.TransferCommand(transfer)));

        String errorMessage = myThrow.getMessage();
        assertTrue(errorMessage.contains("transfer.credit: nao deve ser igual a transfer.debit"));
        assertTrue(errorMessage.contains("transfer.debit: nao deve ser igual a transfer.credit"));
    }
    @Test
    @DisplayName("Transferir com valor insuficiente")
    void transferWithValueInsuficientEquals() {
        Account accountDebit = new Account(6L);
        Account accountCredit = new Account(7L);
        Transfer transfer = new Transfer(accountDebit, accountCredit, new BigDecimal(70));
       var myThrow =  assertThrows(BusinessException.class, () -> port.transfer(new TransferUseCase.TransferCommand(transfer)));

       assertTrue(myThrow.getMessage().contains("""
               Saldo insuficiente."""));
    }

    // positivo get conta
    @Test
    @DisplayName("Transferir com conta de debito, credito e valor existente")
    void transferWithDebitCreditValueExisting() {
        try {
            Account accountDebit = new Account(1L, fifty, new AccountHolder(1L, "Maria"), 1, null, hundred);
            Account accountCredit = new Account(2L, fifty, new AccountHolder(2L, "Joao"), 1, null, hundred);
            Transfer transfer = new Transfer(accountDebit, accountCredit, fifty);
            port.transfer(new TransferUseCase.TransferCommand(transfer));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve lancar excecao quando a conta de debito nao existir")
    void mustThrowExceptionWhenDebitAccountDoesNotExist() {
        //AccountPort accountPort = Mockito.mock(AccountPort.class);

        //Mockito.when(accountPort.getAccount(anyLong())).thenReturn(null);
        assertThrows(RuntimeException.class, () -> port.transfer(new TransferUseCase.TransferCommand(new Transfer(new Account(accountNonexistent), new Account(1L), hundred))));
    }

    @Test
    @DisplayName("Deve lancar excecao quando a conta de debito estiver inativa")
    void anExceptionShouldBeThrownWhenTheDdebitAccountIsInactive() {
        assertThrows(RuntimeException.class, () -> port.transfer(new TransferUseCase.TransferCommand(new Transfer(new Account(8L), new Account(1L), hundred))));
    }

    @Test
    @DisplayName("Deve lancar excecao quando a conta de debito estiver com um correntista inexistente")
    void anExceptionMustBeRaisedWhenTheDebitAccountHasANonexistentAccountHolder() {
        assertThrows(RuntimeException.class, () -> port.transfer(new TransferUseCase.TransferCommand(new Transfer(new Account(5L), new Account(1L), hundred))));
    }

    @Test
    @DisplayName("Deve lancar excecao quando a conta de credito nao existir")
    void mustThrowExceptionWhenCreditAccountDoesNotExist() {
        assertThrows(RuntimeException.class, () -> port.transfer(new TransferUseCase.TransferCommand(new Transfer(new Account(1L), new Account(accountNonexistent), hundred))));
    }

    @Test
    @DisplayName("Deve lancar excecao quando a conta de credito estiver inativa")
    void anExceptionShouldBeThrownWhenTheCreditAccountIsInactive() {
        assertThrows(RuntimeException.class, () -> port.transfer(new TransferUseCase.TransferCommand(new Transfer(new Account(1L), new Account(8L), hundred))));
    }

    @Test
    @DisplayName("Deve lancar excecao quando a conta de credito estiver com um correncista inexistente")
    void anExceptionMustBeRaisedWhenTheCreditAccountHasANonexistentAccountHolder() {
        assertThrows(RuntimeException.class, () -> port.transfer(new TransferUseCase.TransferCommand(new Transfer(new Account(1L), new Account(5L), hundred))));
    }

}
