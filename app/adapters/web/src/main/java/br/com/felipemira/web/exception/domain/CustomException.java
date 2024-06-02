package br.com.felipemira.web.exception.domain;

import br.com.felipemira.common.util.ZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;


@Getter
public abstract class CustomException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Error Datetime", example = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    public ZonedDateTime timestamp;

    public abstract String getException();

    @Schema(description = "List of error messages", example = "[ { \"object\": \"Transfer\", \"field\": \"value\", \"message\": \"deve ser maior que zero\" } ] ")
    public List<ErrorMessage> messages;

    @Schema(description = "Path of the call that caused the error", example = "\\path")
    public String path;

}

