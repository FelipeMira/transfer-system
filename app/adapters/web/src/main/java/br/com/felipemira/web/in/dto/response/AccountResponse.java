package br.com.felipemira.web.in.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Schema
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class AccountResponse implements Serializable {

    @Schema(description = "Numero da Conta", example = "1")
    @JsonProperty("id")
    private Integer number;

    @Schema(description = "Saldo")
    @JsonProperty("saldo")
    private BigDecimal balance;

    @Schema(description = "Correntista")
    @JsonProperty("correntista")
    private AccountHolderResponse accountHolder;

}
