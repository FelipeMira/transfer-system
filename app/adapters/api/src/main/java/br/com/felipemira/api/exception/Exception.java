package br.com.felipemira.api.exception;

import br.com.felipemira.api.domain.exception.ExceptionsMessagesEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class Exception extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private Throwable throwable;
    private HttpStatus status;

    public Exception(HttpStatus status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public Exception(HttpStatus status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public static void checkThrow(final boolean expression, final ExceptionsMessagesEnum exceptionsMessagesEnum) {
        if (expression) {
            exceptionsMessagesEnum.raise();
        }
    }

    public static void checkThrow(final boolean expression, final ExceptionsMessagesEnum exceptionsMessagesEnum, final String message) {
        if (expression) {
            exceptionsMessagesEnum.raise(message);
        }
    }
}
