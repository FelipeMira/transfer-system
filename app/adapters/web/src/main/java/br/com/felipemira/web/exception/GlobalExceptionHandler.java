package br.com.felipemira.web.exception;

import br.com.felipemira.web.exception.domain.CustomException;
import br.com.felipemira.web.exception.domain.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends Exception>, CustomException> EXCEPTION_INFOS = new HashMap<>();

    static {
        EXCEPTION_INFOS.put(org.springframework.web.server.ResponseStatusException.class, new CustomException("Requisicao invalida.", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
        EXCEPTION_INFOS.put(org.springframework.web.servlet.NoHandlerFoundException.class, new CustomException("Recurso nao encontrado.", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
        EXCEPTION_INFOS.put(org.springframework.dao.DataIntegrityViolationException.class, new CustomException("Objeto ja existente.", HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase()));
        EXCEPTION_INFOS.put(org.springframework.web.client.HttpServerErrorException.class, new CustomException("Erro interno do sistema.", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        EXCEPTION_INFOS.put(org.springframework.web.HttpMediaTypeNotAcceptableException.class, new CustomException("Nao aceitavel.", HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()));
        EXCEPTION_INFOS.put(org.springframework.web.client.HttpClientErrorException.UnprocessableEntity.class, new CustomException("Entidade nao processavel.", HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()));
        EXCEPTION_INFOS.put(br.com.felipemira.application.core.exceptions.BusinessException.class, new CustomException("Erro de negocio.", HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomException> handleException(HttpServletRequest request, Exception ex) {
        CustomException customException = EXCEPTION_INFOS.getOrDefault(ex.getClass(), new CustomException("Erro desconhecido", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        HttpStatus status = HttpStatus.valueOf(customException.getStatusCode());
        String errorMessageText = ex.getMessage() != null ? ex.getMessage() : customException.getStatusDescription();
        ErrorMessage errorMessage = new ErrorMessage(null, null, errorMessageText);
        List<ErrorMessage> errorMessages = Collections.singletonList(errorMessage);

        CustomException errorInfo = buildError(request, ex, errorMessages, status);

        return new ResponseEntity<>(errorInfo, status);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody CustomException handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        List<ErrorMessage> errorMessages = ex.getConstraintViolations().stream()
                .map(violation -> {
                    String object = violation.getRootBeanClass().getSimpleName();
                    String field = violation.getPropertyPath().toString();
                    String message = violation.getMessage();

                    return new ErrorMessage(object, field, message);
                })
                .collect(Collectors.toList());

        return buildError(request, ex, errorMessages, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private CustomException buildError(HttpServletRequest request, Exception exception, List<ErrorMessage> messages, HttpStatus httpStatus) {
        return CustomException.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")))
                .messages(messages)
                .statusCode(httpStatus.value())
                .statusDescription(httpStatus.getReasonPhrase())
                .exception(exception.getClass().getSimpleName())
                .path(request.getRequestURI()).build();
    }
}