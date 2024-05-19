package br.com.felipemira.api.service;

import br.com.felipemira.api.domain.exception.ExceptionsMessagesEnum;
import br.com.felipemira.api.domain.request.change.AccountTransferRequest;
import br.com.felipemira.api.domain.response.AccountResponse;
import br.com.felipemira.api.exception.NoContentCustom;
import br.com.felipemira.api.util.GenericConvert;
import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.domain.model.Transfer;
import br.com.felipemira.application.core.ports.in.AccountInfoUseCase;
import br.com.felipemira.application.core.ports.in.TransferUseCase;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class AccountService {

    private final AccountInfoUseCase accountInfoUseCase;
    private final TransferUseCase transferUseCase;

    @Inject
    public AccountService(AccountInfoUseCase accountInfoUseCase, TransferUseCase transferUseCase) {
        this.accountInfoUseCase = accountInfoUseCase;
        this.transferUseCase = transferUseCase;
    }

    public AccountResponse findAccount(Integer number) {
        Account account = new Account(Long.valueOf(number));

        AccountResponse accountResponse = GenericConvert.convertModelMapper(accountInfoUseCase.getAccount(new AccountInfoUseCase.AccountCommand(account)), AccountResponse.class);

        NoContentCustom.checkThrow((accountResponse == null),
                ExceptionsMessagesEnum.GLOBAL_RECURSO_NAO_ENCONTRADO);

        return accountResponse;
    }

    public String transfer(Integer idDebit, Integer idCredit, AccountTransferRequest contaTransferRequest) {

        String mensagem = "";

        Transfer transfer = new Transfer(new Account(Long.valueOf(idDebit)), new Account(Long.valueOf(idCredit)), new BigDecimal(String.valueOf(contaTransferRequest.getTransferValue())));

        transferUseCase.transfer(new TransferUseCase.TransferCommand(transfer));
        mensagem = "Transferencia realizada com sucesso";


        return mensagem;
    }

}
