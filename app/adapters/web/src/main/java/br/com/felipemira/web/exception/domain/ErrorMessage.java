package br.com.felipemira.web.exception.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    @Schema(description = "Nome do objeto que causou o erro", example = "Transfer")
    private String object;
    @Schema(description = "Nome do campo que causou o erro", example = "value")
    private String field;
    @Schema(description = "Mensagem de erro", example = "deve ser maior que zero")
    private String message;
}