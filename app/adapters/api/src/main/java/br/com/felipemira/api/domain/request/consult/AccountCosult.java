package br.com.felipemira.api.domain.request.consult;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCosult implements Serializable {

    @Schema(description = "numero da conta",
            example = "1")
    private Integer number;

}
