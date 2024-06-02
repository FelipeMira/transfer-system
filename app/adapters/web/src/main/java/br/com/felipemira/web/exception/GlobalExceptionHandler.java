package br.com.felipemira.web.exception;

import br.com.felipemira.application.core.exceptions.NotFoundException;
import br.com.felipemira.web.exception.domain.CustomException;
import br.com.felipemira.web.exception.domain.ErrorMessage;
import br.com.felipemira.web.exception.domain.ErrorStrategy;
import br.com.felipemira.web.exception.domain.errors.*;
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

    private static final Map<Class<? extends Exception>, ErrorStrategy> EXCEPTION_STRATEGIES = new HashMap<>();

    static {
        EXCEPTION_STRATEGIES.put(org.springframework.web.server.ResponseStatusException.class, new BadRequestError("Requisicao invalida.", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
        EXCEPTION_STRATEGIES.put(org.springframework.web.servlet.NoHandlerFoundException.class, new NotFoundError("Recurso nao encontrado.", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
        EXCEPTION_STRATEGIES.put(br.com.felipemira.application.core.exceptions.NotFoundException.class, new NotFoundError("Recurso nao encontrado.", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
        EXCEPTION_STRATEGIES.put(org.springframework.dao.DataIntegrityViolationException.class, new ConflictError("Objeto ja existente.", HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase()));
        EXCEPTION_STRATEGIES.put(org.springframework.web.client.HttpServerErrorException.class, new InternalServerError("Erro interno do sistema.", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        EXCEPTION_STRATEGIES.put(org.springframework.web.HttpMediaTypeNotAcceptableException.class, new NotAcceptableError("Nao aceitavel.", HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()));
        EXCEPTION_STRATEGIES.put(org.springframework.web.client.HttpClientErrorException.UnprocessableEntity.class, new UnprocessableEntityError("Entidade nao processavel.", HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()));
        EXCEPTION_STRATEGIES.put(br.com.felipemira.application.core.exceptions.BusinessException.class, new BusinessError("Erro de negocio.", HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomException> handleException(HttpServletRequest request, Exception ex) {
        ErrorStrategy errorStrategy = EXCEPTION_STRATEGIES.getOrDefault(ex.getClass(), new InternalServerError("Erro desconhecido", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        HttpStatus status = HttpStatus.valueOf(errorStrategy.getStatusCode());
        String errorMessageText = ex.getMessage() != null ? ex.getMessage() : errorStrategy.getStatusDescription();
        ErrorMessage errorMessage = new ErrorMessage(null, null, errorMessageText);
        List<ErrorMessage> errorMessages = Collections.singletonList(errorMessage);

        CustomException errorInfo = errorStrategy.buildError(request, ex, errorMessages, status);

        return new ResponseEntity<>(errorInfo, status);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody CustomException handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        ErrorStrategy errorStrategy = EXCEPTION_STRATEGIES.getOrDefault(ex.getClass(), new InternalServerError("Erro desconhecido", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        List<ErrorMessage> errorMessages = ex.getConstraintViolations().stream()
                .map(violation -> {
                    String object = violation.getRootBeanClass().getSimpleName();
                    String field = violation.getPropertyPath().toString();
                    String message = violation.getMessage();

                    return new ErrorMessage(object, field, message);
                })
                .collect(Collectors.toList());

        return errorStrategy.buildError(request, ex, errorMessages, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}