package br.com.felipemira.api.domain.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ErrorInfoAnotherApi implements Serializable {

    private static final long serialVersionUID = 1L;

    public LocalDateTime timestamp;

    public String exception;

    public String message;

    public String path;
}

