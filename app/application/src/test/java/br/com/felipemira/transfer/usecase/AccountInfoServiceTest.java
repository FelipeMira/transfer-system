package br.com.felipemira.transfer.usecase;

import br.com.felipemira.transfer.system.domain.model.Account;
import br.com.felipemira.transfer.system.exceptions.BusinessException;
import br.com.felipemira.transfer.system.ports.in.AccountInfoUseCase;
import br.com.felipemira.transfer.system.ports.in.AccountInfoUseCase.AccountCommand;
import br.com.felipemira.transfer.system.ports.out.AccountPort;
import br.com.felipemira.transfer.system.ports.out.RegisterPort;
import br.com.felipemira.transfer.usecase.builds.BuildOne;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Caso de Uso - Servico de Transferencia")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BuildOne.class)
class AccountInfoServiceTest {

    @Autowired
    private AccountPort accountPort;

    @Autowired
    private RegisterPort registerPort;

    @Autowired
    private AccountInfoUseCase accountInfoUseCase;

    @Test
    @DisplayName("Should return account when account exists and is active")
    void shouldReturnAccountWhenAccountExistsAndIsActive() {
        AccountCommand accountCommand = new AccountCommand(new Account(4L));
        Account expectedAccount = new Account(4L, new BigDecimal(1000L), registerPort.getAccountHolder(4L), 1, null, new BigDecimal(1000));

        Account actualAccount = accountInfoUseCase.getAccount(accountCommand);

        assertEquals(expectedAccount.toString(), actualAccount.toString());
    }

    @Test
    @DisplayName("Should throw exception when account does not exist")
    void shouldThrowExceptionWhenAccountDoesNotExist() {
        AccountCommand accountCommand = new AccountCommand(new Account(100L));

        assertThrows(RuntimeException.class, () -> accountInfoUseCase.getAccount(accountCommand));
    }

    @Test
    @DisplayName("Should throw exception when account holder does not exist")
    void shouldThrowExceptionWhenAccountHolderDoesNotExist() {
        try{
            var account = accountInfoUseCase.getAccount(new AccountCommand(new Account(5L)));

        }catch(BusinessException e){
            assertEquals(e.getMessage(), "O correntista com id 5 e inexistente.");
        }

        //RegisterPort registerPort = Mockito.mock(RegisterPort.class);

        //Mockito.when(registerPort.getAccountHolder(account.getAccountHolder().getIdAccountHolder())).thenReturn(null);
    }
}
