package br.com.felipemira.web.exception.domain.errors;

import br.com.felipemira.web.exception.domain.CustomException;
import br.com.felipemira.web.exception.domain.ErrorMessage;
import br.com.felipemira.web.exception.domain.ErrorStrategy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Schema(description = "Conflict error response")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ConflictError extends CustomException implements ErrorStrategy {

    public ConflictError(String message, int statusCode, String statusDescription) {
        this.timestamp = ZonedDateTime.now();
        this.messages = Collections.singletonList(new ErrorMessage(null, null, message));
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.exception = null;
        this.path = null;
    }

    @Override
    public CustomException buildError(HttpServletRequest request, Exception exception, List<ErrorMessage> messages, HttpStatus httpStatus) {
        this.timestamp = LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo"));
        this.messages = messages;
        this.statusCode = httpStatus.value();
        this.statusDescription = httpStatus.getReasonPhrase();
        this.exception = exception.getClass().getSimpleName();
        this.path = request.getRequestURI();
        return this;
    }

    @Schema(description = "Status code", example = "409")
    private int statusCode;

    @Schema(description = "Status Description", example = "Conflict")
    private String statusDescription;

    @Schema(description = "Thrown exception", example = "ConflictException")
    private String exception;

}
