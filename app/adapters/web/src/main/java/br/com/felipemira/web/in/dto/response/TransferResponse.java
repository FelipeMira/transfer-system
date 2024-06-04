package br.com.felipemira.web.in.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Schema
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class TransferResponse implements Serializable {

    @Schema(description = "Resposta da solicitação", example = "Transferencia realizada com sucesso", name = "mensagem")
    @JsonProperty("mensagem")
    private String message;
}

