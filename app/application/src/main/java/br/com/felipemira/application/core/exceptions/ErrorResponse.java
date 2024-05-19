package br.com.felipemira.application.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter@Setter
public class ErrorResponse {

    private List<ErrorDetail> errors;

}
