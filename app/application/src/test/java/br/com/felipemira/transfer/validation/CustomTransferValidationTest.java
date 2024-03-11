package br.com.felipemira.transfer.validation;

import br.com.felipemira.transfer.system.domain.model.Account;
import br.com.felipemira.transfer.system.domain.model.AccountHolder;
import br.com.felipemira.transfer.system.domain.model.Transfer;
import br.com.felipemira.transfer.system.ports.in.TransferUseCase;
import br.com.felipemira.transfer.usecase.builds.BuildOne;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CustomTransferValidation")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BuildOne.class)
public class CustomTransferValidationTest {

    Account accountDebit = new Account(1L, new BigDecimal(1000), new AccountHolder(1L, "maria"), 1, null, new BigDecimal(1000));
    Account accountCredit = new Account(2L, new BigDecimal(1000), new AccountHolder(2L, "joao"), 1, null, new BigDecimal(1000));

    @Test
    @DisplayName("Nao deve retornar nenhuma exception quando todos os campos forem validos e as contas de debito e credito forem diferentes")
    void shouldReturnTrueWhenAllFieldsAreValidAndDebitAndCreditAccountsAreDifferent() {
        Transfer transfer = new Transfer(accountDebit, accountCredit, new BigDecimal(100));
        assertDoesNotThrow(() -> new TransferUseCase.TransferCommand(transfer));
    }

    @Test
    @DisplayName("Deve retornar erro quando a conta de debito for nula")
    void shouldReturnFalseWhenDebitAccountIsNull() {
        assertThrows(Exception.class, () -> new TransferUseCase.TransferCommand(new Transfer(null, accountCredit, new BigDecimal(100))));
    }

    @Test
    @DisplayName("Deve retornar erro quando o valor for menor que zero")
    void IshouldReturnErrorWhenValueLessZero() {
        var myThrow =  assertThrows(ConstraintViolationException.class, () -> new TransferUseCase.TransferCommand(new Transfer(accountDebit, accountCredit, new BigDecimal(-1))));

        assertEquals("""
                transfer:\s
                One or more violations found:\s
                Insufficient value""", myThrow.getMessage());
    }
}
