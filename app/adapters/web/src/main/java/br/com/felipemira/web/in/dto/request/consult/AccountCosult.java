package br.com.felipemira.web.in.dto.request.consult;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Min(1)
    private Integer number;

}
