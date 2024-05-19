package br.com.felipemira.api.resource.controller;

import br.com.felipemira.api.domain.request.change.AccountTransferRequest;
import br.com.felipemira.api.resource.definition.TransferDefinition;
import br.com.felipemira.api.service.AccountService;
import br.com.felipemira.api.util.AppConstantes;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = AppConstantes.PATH_TRANSFERENCIA)
public class TransferController implements TransferDefinition {

    private final AccountService contaService;

    @Inject
    public TransferController(AccountService contaService) {
        this.contaService = contaService;
    }

    @Override
    @ResponseStatus(OK)
    @PostMapping(value = "/{idDebit}/contas/{idCredit}")
    public String transfer(@Parameter(description="Id da conta de debito", required=true) @PathVariable Integer idDebit,
                             @Parameter(description="Id da conta de credito", required=true) @PathVariable Integer idCredit,
                             @Parameter(description="Valor da transferencia",
                                     required=true, schema=@Schema(implementation = AccountTransferRequest.class))
                                 @Valid @RequestBody AccountTransferRequest contaTransferRequest) throws IOException {

        return contaService.transfer(idDebit, idCredit, contaTransferRequest);
    }


}
