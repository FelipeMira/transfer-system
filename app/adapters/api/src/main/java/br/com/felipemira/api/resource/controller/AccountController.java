package br.com.felipemira.api.resource.controller;

import br.com.felipemira.api.domain.response.AccountResponse;
import br.com.felipemira.api.resource.definition.AccountDefinition;
import br.com.felipemira.api.service.AccountService;
import br.com.felipemira.api.util.AppConstantes;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.management.BadAttributeValueExpException;
import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = AppConstantes.PATH_CONTAS)
public class AccountController implements AccountDefinition {


    private final AccountService contaService;

    @Inject
    public AccountController(AccountService contaService) {
        this.contaService = contaService;
    }

    @Override
    @ResponseStatus(OK)
    @GetMapping(produces= APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<?> findAccount(
            @Valid @Parameter(description="id da conta", required=true) @PathVariable Integer id) throws JsonParseException, IOException, BadAttributeValueExpException {
        AccountResponse response = contaService.findAccount(id);

        return ResponseEntity.ok(response);
    }


}
