package br.com.felipemira.web.exception.domain;

import br.com.felipemira.common.util.ZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta de erro da API")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Getter
public class CustomException implements Serializable {

    private static final long serialVersionUID = 1L;

    public CustomException(String message, int statusCode, String statusDescription) {
        this.timestamp = ZonedDateTime.now();
        this.messages = Collections.singletonList(new ErrorMessage(null, null, message));
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.exception = null;
        this.path = null;
    }

    @Schema(description = "Data e hora do erro", example = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    public ZonedDateTime timestamp;

    @Schema(description = "Codigo do erro", example = "404")
    public Integer statusCode;

    @Schema(description = "Descricao do status HTTP", example = "Not Found")
    public String statusDescription;

    @Schema(description = "Excecao lancada", example = "Exception")
    public String exception;

    @Schema(description = "Lista de mensagens de erro", example = "[ { \"object\": \"Transfer\", \"field\": \"value\", \"message\": \"deve ser maior que zero\" } ] ")
    public List<ErrorMessage> messages;

    @Schema(description = "Path da chamada que ocasionou o erro", example = "\\path")
    public String path;

}

