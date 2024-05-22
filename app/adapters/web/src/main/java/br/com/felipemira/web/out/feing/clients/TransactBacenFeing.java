package br.com.felipemira.web.out.feing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "bacen", url = "${spring.cloud.openfeign.client.config.bacen.url}")
public interface TransactBacenFeing {

    @PostMapping
    ResponseEntity<?> postTransact(@RequestBody String transacaoBacenRequest, @RequestHeader Map<String, String> headers);
}
