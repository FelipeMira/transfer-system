package br.com.felipemira.api.resource.definition;

import br.com.felipemira.api.domain.exception.ErroInfo;
import br.com.felipemira.api.domain.response.AccountResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.BadAttributeValueExpException;
import java.io.IOException;

import static br.com.felipemira.api.exception.GlobalExceptionHandler.*;


@SuppressWarnings("unused")
@Tag(name = "Contas", description = "Recuperar Contas")
public interface AccountDefinition {


    @Operation(
            summary = "Consulta uma conta",
            description = "Retorna a conta consultada pelo numero",
            tags = {"Contas"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta encontrada", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
            @ApiResponse(responseCode = "400", description = MENSAGEM_GLOBAL_400, content = @Content(schema = @Schema(implementation = ErroInfo.class))),
            @ApiResponse(responseCode = "404", description = MENSAGEM_GLOBAL_404, content = @Content(schema = @Schema(implementation = ErroInfo.class))),
            @ApiResponse(responseCode = "500", description = MENSAGEM_GLOBAL_500, content = @Content(schema = @Schema(implementation = ErroInfo.class)))
    })
    @ResponseBody
    ResponseEntity<?> findAccount(@Parameter(description="Id da conta", required=true) @PathVariable Integer id)
            throws IOException, BadAttributeValueExpException;


}
