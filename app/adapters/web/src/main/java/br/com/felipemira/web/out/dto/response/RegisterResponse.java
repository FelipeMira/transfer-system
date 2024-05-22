package br.com.felipemira.web.out.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponse {

    private Integer idCorrentista;
    private String nome;
}
