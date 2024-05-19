package br.com.felipemira.api.exception.strategy;

import org.springframework.web.client.HttpClientErrorException;

public interface HttpStatusStrategy {
    String handleException(HttpClientErrorException ex);
}

