package br.com.felipemira.web.exception;

import br.com.felipemira.web.exception.domain.ErroInfo;
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

    private static final Map<Class<? extends Exception>, ExceptionInfo> EXCEPTION_INFOS = new HashMap<>();

    static {
        EXCEPTION_INFOS.put(org.springframework.web.server.ResponseStatusException.class, new ExceptionInfo("Requisicao invalida.", HttpStatus.BAD_REQUEST));
        EXCEPTION_INFOS.put(org.springframework.web.servlet.NoHandlerFoundException.class, new ExceptionInfo("Recurso nao encontrado.", HttpStatus.NOT_FOUND));
        EXCEPTION_INFOS.put(org.springframework.dao.DataIntegrityViolationException.class, new ExceptionInfo("Objeto ja existente.", HttpStatus.CONFLICT));
        EXCEPTION_INFOS.put(org.springframework.web.client.HttpServerErrorException.class, new ExceptionInfo("Erro interno do sistema.", HttpStatus.INTERNAL_SERVER_ERROR));
        EXCEPTION_INFOS.put(org.springframework.web.HttpMediaTypeNotAcceptableException.class, new ExceptionInfo("Nao aceitavel.", HttpStatus.NOT_ACCEPTABLE));
        EXCEPTION_INFOS.put(org.springframework.web.client.HttpClientErrorException.UnprocessableEntity.class, new ExceptionInfo("Entidade nao processavel.", HttpStatus.UNPROCESSABLE_ENTITY));
        EXCEPTION_INFOS.put(br.com.felipemira.application.core.exceptions.BusinessException.class, new ExceptionInfo("Erro de negocio.", HttpStatus.UNPROCESSABLE_ENTITY));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroInfo> handleException(HttpServletRequest request, Exception ex) {
        ExceptionInfo exceptionInfo = EXCEPTION_INFOS.getOrDefault(ex.getClass(), new ExceptionInfo("Erro desconhecido", HttpStatus.INTERNAL_SERVER_ERROR));

        String errorMessageText = ex.getMessage() != null ? ex.getMessage() : exceptionInfo.getMessage();
        ErrorMessage errorMessage = new ErrorMessage(null, null, errorMessageText);
        List<ErrorMessage> errorMessages = Collections.singletonList(errorMessage);

        ErroInfo errorInfo = buildErrorInfo(request, ex, errorMessages, exceptionInfo.getStatus());

        return new ResponseEntity<>(errorInfo, exceptionInfo.getStatus());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody ErroInfo handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        List<ErrorMessage> errorMessages = ex.getConstraintViolations().stream()
                .map(violation -> {
                    String object = violation.getRootBeanClass().getSimpleName();
                    String field = violation.getPropertyPath().toString();
                    String message = violation.getMessage();

                    return new ErrorMessage(object, field, message);
                })
                .collect(Collectors.toList());

        return buildErrorInfo(request, ex, errorMessages, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ErroInfo buildErrorInfo(HttpServletRequest request, Exception exception, List<ErrorMessage> messages, HttpStatus httpStatus) {
        return ErroInfo.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")))
                .messages(messages)
                .code(httpStatus.value())
                .exception(exception.getClass().getSimpleName())
                .path(request.getRequestURI()).build();
    }

    private static class ExceptionInfo {
        private final String message;
        private final HttpStatus status;

        public ExceptionInfo(String message, HttpStatus status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public HttpStatus getStatus() {
            return status;
        }
    }
}
