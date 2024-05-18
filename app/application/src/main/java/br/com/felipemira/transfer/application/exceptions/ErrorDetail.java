package br.com.felipemira.transfer.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter@Setter
public class ErrorDetail {

    private String message;
    private String detail;

}
