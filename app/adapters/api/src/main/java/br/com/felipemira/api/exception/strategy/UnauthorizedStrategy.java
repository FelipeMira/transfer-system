package br.com.felipemira.api.exception.strategy;

import org.springframework.web.client.HttpClientErrorException;

public class UnauthorizedStrategy implements HttpStatusStrategy {
    @Override
    public String handleException(HttpClientErrorException ex) {
        return "MENSAGEM_GLOBAL_401";
    }
}
