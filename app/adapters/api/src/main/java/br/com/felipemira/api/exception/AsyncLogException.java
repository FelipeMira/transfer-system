package br.com.felipemira.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncLogException {

    @Async
    public void logAsync(Throwable t) {
        log.error(t.getMessage(), t);
    }

}

