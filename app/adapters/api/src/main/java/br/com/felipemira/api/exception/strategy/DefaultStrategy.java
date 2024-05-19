package br.com.felipemira.api.exception.strategy;

import org.springframework.web.client.HttpClientErrorException;

public class DefaultStrategy implements HttpStatusStrategy {
    @Override
    public String handleException(HttpClientErrorException ex) {
        return ex.getMessage();
    }
}
