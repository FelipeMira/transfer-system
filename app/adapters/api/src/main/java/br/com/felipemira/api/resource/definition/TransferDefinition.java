package br.com.felipemira.api.resource.definition;

import br.com.felipemira.api.domain.exception.ErroInfo;
import br.com.felipemira.api.domain.request.change.AccountTransferRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static br.com.felipemira.api.exception.GlobalExceptionHandler.*;

@SuppressWarnings("unused")
@Tag(name = "Transferencias", description = "Realizar Transferencias")
public interface TransferDefinition {



    @Operation(
        summary = "Efetiva a transferencia",
        description = "Transmite o valor da conta de debito para a conta de credito",
        tags = {"Transferencias"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferencia realizda com sucesso!", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = MENSAGEM_GLOBAL_400, content = @Content(schema = @Schema(implementation = ErroInfo.class))),
            @ApiResponse(responseCode = "404", description = MENSAGEM_GLOBAL_404, content = @Content(schema = @Schema(implementation = ErroInfo.class))),
            @ApiResponse(responseCode = "422", description = MENSAGEM_GLOBAL_422, content = @Content(schema = @Schema(implementation = ErroInfo.class))),
            @ApiResponse(responseCode = "500", description = MENSAGEM_GLOBAL_500, content = @Content(schema = @Schema(implementation = ErroInfo.class)))
    })
    @ResponseBody
    String transfer(@Parameter(description="Id da conta de debito", required=true) @PathVariable Integer idDebit,
                      @Parameter(description="Id da conta de credito", required=true) @PathVariable Integer idCredit,
                      @Parameter(description="Valor da transferencia",
                              required=true, schema=@Schema(implementation = AccountTransferRequest.class))
                      @Valid @RequestBody AccountTransferRequest contaTransferRequest)
            throws IOException;

}
