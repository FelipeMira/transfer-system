package br.com.felipemira.web.out.feing.clients;

import br.com.felipemira.web.out.dto.response.RegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cadastro", url = "${spring.cloud.openfeign.client.config.cadastro.url}")
public interface RegisterHolderAccountFeing {

    @GetMapping(path = "{id}")
    RegisterResponse getRegister(@PathVariable("id") Long id);
}
