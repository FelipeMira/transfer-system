package br.com.felipemira.transfer.exceptions;

import br.com.felipemira.transfer.system.exceptions.BusinessException;
import br.com.felipemira.transfer.system.exceptions.Error;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ErrorTest {

    @Test
    @DisplayName("Should throw BusinessException when mandatory field is null")
    void shouldThrowBusinessExceptionWhenMandatoryFieldIsNull() {
        assertThrows(BusinessException.class, () -> Error.mandatory(null));
    }

    @Test
    @DisplayName("Should throw BusinessException when nonexistent field is accessed")
    void shouldThrowBusinessExceptionWhenNonexistentFieldIsAccessed() {
        assertThrows(BusinessException.class, () -> Error.nonexistent(null));
    }

    @Test
    @DisplayName("Should throw BusinessException when account holder does not exist")
    void shouldThrowBusinessExceptionWhenAccountHolderDoesNotExist() {
        assertThrows(BusinessException.class, () -> Error.accountHolderNonexistent(100L));
    }

    @Test
    @DisplayName("Should throw BusinessException when account is inactive")
    void shouldThrowBusinessExceptionWhenAccountIsInactive() {
        assertThrows(BusinessException.class, () -> Error.inactive(1L));
    }

    @Test
    @DisplayName("Should throw BusinessException when funds are insufficient")
    void shouldThrowBusinessExceptionWhenFundsAreInsufficient() {
        assertThrows(BusinessException.class, Error::insufficientFunds);
    }

    @Test
    @DisplayName("Should throw BusinessException when debit and credit accounts are the same")
    void shouldThrowBusinessExceptionWhenDebitAndCreditAccountsAreTheSame() {
        assertThrows(BusinessException.class, Error::sameAccount);
    }

    @Test
    @DisplayName("Should throw BusinessException when date is not current")
    void shouldThrowBusinessExceptionWhenDateIsNotCurrent() {
        assertThrows(BusinessException.class, Error::dateNotCurrent);
    }

    @Test
    @DisplayName("Should throw BusinessException when transaction is above daily limit")
    void shouldThrowBusinessExceptionWhenTransactionIsAboveDailyLimit() {
        assertThrows(BusinessException.class, () -> Error.aboveDailyLimit(new BigDecimal(1000)));
    }
}
