package br.com.felipemira.web.in.definition;

import br.com.felipemira.web.exception.domain.CustomException;
import br.com.felipemira.web.in.dto.request.change.AccountTransferRequest;
import br.com.felipemira.web.util.AppConstantes;
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

@SuppressWarnings("unused")
@Tag(name = "Transferencias", description = "Realizar Transferencias")
public interface TransferDefinition {



    @Operation(
        summary = "Efetiva a transferencia",
        description = "Transmite o valor da conta de debito para a conta de credito",
        tags = {"Transferencias"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_200, description = AppConstantes.DESCRIPTION_TRANSFERENCIA_200, content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_400, description = AppConstantes.DESCRIPTION_400, content = @Content(schema = @Schema(implementation = CustomException.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_404, description = AppConstantes.DESCRIPTION_404, content = @Content(schema = @Schema(implementation = CustomException.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_422, description = AppConstantes.DESCRIPTION_422, content = @Content(schema = @Schema(implementation = CustomException.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_500, description = AppConstantes.DESCRIPTION_500, content = @Content(schema = @Schema(implementation = CustomException.class)))
    })
    @ResponseBody
    String transfer(@Parameter(description="Id da conta de debito", required=true) @PathVariable Integer idDebit,
                      @Parameter(description="Id da conta de credito", required=true) @PathVariable Integer idCredit,
                      @Parameter(description="Valor da transferencia",
                              required=true, schema=@Schema(implementation = AccountTransferRequest.class))
                      @Valid @RequestBody AccountTransferRequest contaTransferRequest)
            throws IOException;

}
