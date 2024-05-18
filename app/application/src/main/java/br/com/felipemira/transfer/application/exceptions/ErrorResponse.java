package br.com.felipemira.transfer.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter@Setter
public class ErrorResponse {

    private List<ErrorDetail> errors;

}
