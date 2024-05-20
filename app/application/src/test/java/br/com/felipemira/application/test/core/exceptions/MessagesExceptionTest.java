package br.com.felipemira.application.test.core.exceptions;

import br.com.felipemira.application.core.exceptions.BusinessException;
import br.com.felipemira.application.core.exceptions.MessagesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MessagesExceptionTest {

    @Test
    @DisplayName("Should throw BusinessException when mandatory field is null")
    void shouldThrowBusinessExceptionWhenMandatoryFieldIsNull() {
        assertThrows(BusinessException.class, () -> MessagesException.mandatory(null));
    }

    @Test
    @DisplayName("Should throw BusinessException when nonexistent field is accessed")
    void shouldThrowBusinessExceptionWhenNonexistentFieldIsAccessed() {
        assertThrows(BusinessException.class, () -> MessagesException.nonexistent(null));
    }

    @Test
    @DisplayName("Should throw BusinessException when account holder does not exist")
    void shouldThrowBusinessExceptionWhenAccountHolderDoesNotExist() {
        assertThrows(BusinessException.class, () -> MessagesException.accountHolderNonexistent(100L));
    }

    @Test
    @DisplayName("Should throw BusinessException when account is inactive")
    void shouldThrowBusinessExceptionWhenAccountIsInactive() {
        assertThrows(BusinessException.class, () -> MessagesException.inactive(1L));
    }

    @Test
    @DisplayName("Should throw BusinessException when funds are insufficient")
    void shouldThrowBusinessExceptionWhenFundsAreInsufficient() {
        assertThrows(BusinessException.class, MessagesException::insufficientFunds);
    }

    @Test
    @DisplayName("Should throw BusinessException when debit and credit accounts are the same")
    void shouldThrowBusinessExceptionWhenDebitAndCreditAccountsAreTheSame() {
        assertThrows(BusinessException.class, MessagesException::sameAccount);
    }

    @Test
    @DisplayName("Should throw BusinessException when date is not current")
    void shouldThrowBusinessExceptionWhenDateIsNotCurrent() {
        assertThrows(BusinessException.class, MessagesException::dateNotCurrent);
    }

    @Test
    @DisplayName("Should throw BusinessException when transaction is above daily limit")
    void shouldThrowBusinessExceptionWhenTransactionIsAboveDailyLimit() {
        assertThrows(BusinessException.class, () -> MessagesException.aboveDailyLimit(new BigDecimal(1000)));
    }
}
