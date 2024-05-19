package br.com.felipemira.api.domain.response;

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
public class AccountHolderResponse implements Serializable {

    @Schema(description = "Nome Correntista")
    @JsonProperty("nomeCorrentista")
    private String name;

    @Schema(description = "Id Correntista")
    @JsonProperty("idCorrentista")
    private Long idAccountHolder;
}
