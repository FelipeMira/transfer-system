package br.com.felipemira.web.in.controller;

import br.com.felipemira.web.in.dto.request.change.AccountTransferRequest;
import br.com.felipemira.web.in.definition.TransferDefinition;
import br.com.felipemira.web.util.AppConstantes;
import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.domain.model.Transfer;
import br.com.felipemira.application.core.ports.in.TransferUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = AppConstantes.PATH_TRANSFERENCIA)
public class TransferController implements TransferDefinition {

    private final TransferUseCase transferUseCase;

    @Inject
    public TransferController(TransferUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    @Override
    @ResponseStatus(OK)
    @PostMapping(value = "/{idDebit}/contas/{idCredit}")
    public String transfer(@Parameter(description="Id da conta de debito", required=true) @PathVariable Integer idDebit,
                             @Parameter(description="Id da conta de credito", required=true) @PathVariable Integer idCredit,
                             @Parameter(description="Valor da transferencia",
                                     required=true, schema=@Schema(implementation = AccountTransferRequest.class))
                                 @Valid @RequestBody AccountTransferRequest contaTransferRequest) throws IOException {

        String mensagem = "";

        Transfer transfer = new Transfer(new Account(Long.valueOf(idDebit)), new Account(Long.valueOf(idCredit)), new BigDecimal(String.valueOf(contaTransferRequest.getTransferValue())));

        transferUseCase.transfer(new TransferUseCase.TransferCommand(transfer));
        mensagem = "Transferencia realizada com sucesso";

        return mensagem;
    }


}
