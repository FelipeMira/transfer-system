package br.com.felipemira.web.exception.domain;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ErrorStrategy {
    CustomException buildError(HttpServletRequest request, Exception exception, List<ErrorMessage> messages, HttpStatus httpStatus);
    int getStatusCode();
    String getStatusDescription();
}
