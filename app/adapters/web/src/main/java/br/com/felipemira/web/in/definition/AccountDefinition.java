package br.com.felipemira.web.in.definition;

import br.com.felipemira.web.exception.domain.CustomException;
import br.com.felipemira.web.exception.domain.errors.BadRequestError;
import br.com.felipemira.web.exception.domain.errors.InternalServerError;
import br.com.felipemira.web.exception.domain.errors.NotFoundError;
import br.com.felipemira.web.exception.domain.errors.UnprocessableEntityError;
import br.com.felipemira.web.in.dto.response.AccountResponse;
import br.com.felipemira.common.domain.pagination.AppPage;
import br.com.felipemira.web.util.AppConstantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.BadAttributeValueExpException;
import java.io.IOException;


@SuppressWarnings("unused")
@Tag(name = AppConstantes.TAG_CONTAS, description = "Recuperar Contas")
public interface AccountDefinition {


    @Operation(
            summary = "Consulta uma conta",
            description = "Retorna a conta consultada pelo numero",
            tags = {AppConstantes.TAG_CONTAS}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_200 , description = AppConstantes.DESCRIPTION_CONTA_200, content = @Content(schema = @Schema(implementation = AccountResponse.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_400, description = AppConstantes.DESCRIPTION_400, content = @Content(schema = @Schema(implementation = BadRequestError.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_404, description = AppConstantes.DESCRIPTION_404, content = @Content(schema = @Schema(implementation = NotFoundError.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_422, description = AppConstantes.DESCRIPTION_422, content = @Content(schema = @Schema(implementation = UnprocessableEntityError.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_500, description = AppConstantes.DESCRIPTION_500, content = @Content(schema = @Schema(implementation = InternalServerError.class)))
    })
    @ResponseBody
    ResponseEntity<?> findAccount(@Parameter(description="Id da conta", required=true) @PathVariable Integer id)
            throws IOException, BadAttributeValueExpException;

    @Operation(
            summary = "Consulta todas as contas",
            description = "Retorna todas as contas paginadas",
            tags = {AppConstantes.TAG_CONTAS}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_200, description = AppConstantes.DESCRIPTION_CONTA_200, content = @Content(schema = @Schema(implementation = AccountResponse.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_400, description = AppConstantes.DESCRIPTION_400, content = @Content(schema = @Schema(implementation = BadRequestError.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_404, description = AppConstantes.DESCRIPTION_404, content = @Content(schema = @Schema(implementation = NotFoundError.class))),
            @ApiResponse(responseCode = AppConstantes.RESPONSE_CODE_500, description = AppConstantes.DESCRIPTION_500, content = @Content(schema = @Schema(implementation = InternalServerError.class)))
    })
    ResponseEntity<AppPage<AccountResponse>> fetchAccounts(
            @Parameter(description = "Page number", example = "0") @RequestParam @Min(value = 0, message = "Page number must be equal or greater than 0") int page,
            @Parameter(description = "Page size", example = "3") @RequestParam @Min(value = 1, message = "Size of records must be equal or greater than 1") int size
    );
}
