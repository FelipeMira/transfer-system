package br.com.felipemira.api.exception.strategy;

import org.springframework.web.client.HttpClientErrorException;

public class UnprocessableEntityStrategy implements HttpStatusStrategy {
    @Override
    public String handleException(HttpClientErrorException ex) {
        return ex.getMessage();
    }
}
