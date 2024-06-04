package br.com.felipemira.web.in.controller;

import br.com.felipemira.web.in.dto.request.change.AccountTransferRequest;
import br.com.felipemira.web.in.definition.TransferDefinition;
import br.com.felipemira.web.in.dto.response.TransferResponse;
import br.com.felipemira.web.util.AppConstantes;
import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.domain.model.Transfer;
import br.com.felipemira.application.core.ports.in.TransferUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    @PostMapping(produces= APPLICATION_JSON_VALUE)
    public ResponseEntity<?> transfer(
                             @Parameter(description="Dados da transferencia",
                                     required=true, schema=@Schema(implementation = AccountTransferRequest.class))
                                 @Valid @RequestBody AccountTransferRequest accountTransferRequest) throws IOException {


        Transfer transfer = new Transfer(new Account(Long.valueOf(accountTransferRequest.getAccountDebit())), new Account(Long.valueOf(accountTransferRequest.getAccountCredit())), new BigDecimal(String.valueOf(accountTransferRequest.getTransferValue())));

        transferUseCase.transfer(new TransferUseCase.TransferCommand(transfer));

        TransferResponse transferResponse = new TransferResponse("Transferencia realizada com sucesso");

        return ResponseEntity.ok(transferResponse);
    }


}
