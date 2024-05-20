package br.com.felipemira.web.in.controller;

import br.com.felipemira.common.domain.pagination.AppPageable;
import br.com.felipemira.web.in.dto.response.AccountResponse;
import br.com.felipemira.web.in.definition.AccountDefinition;
import br.com.felipemira.web.util.AppConstantes;
import br.com.felipemira.web.util.GenericConvert;
import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.ports.in.AccountInfoUseCase;
import br.com.felipemira.common.domain.pagination.AppPage;
import br.com.felipemira.common.util.pagination.PaginationUtils;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = AppConstantes.PATH_CONTAS)
public class AccountController implements AccountDefinition {


    private final AccountInfoUseCase accountInfoUseCase;

    @Inject
    public AccountController(AccountInfoUseCase accountInfoUseCase) {
        this.accountInfoUseCase = accountInfoUseCase;
    }

    @Override
    @ResponseStatus(OK)
    @GetMapping(produces= APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<?> findAccount(
            @Valid @Parameter(description="id da conta", required=true) @PathVariable Integer id) throws JsonParseException {
        Account account = new Account(Long.valueOf(id));

        AccountResponse accountResponse = GenericConvert.convertModelMapper(accountInfoUseCase.getAccount(new AccountInfoUseCase.AccountCommand(account)), AccountResponse.class);

        return ResponseEntity.ok(accountResponse);
    }

    @Override
    @ResponseStatus(OK)
    @GetMapping
    public ResponseEntity<AppPage<AccountResponse>> fetchAccounts(int page, int size) {
        AppPage<Account> accounts = accountInfoUseCase.fetchAccounts(new AccountInfoUseCase.AccountPageableCommand(new AppPageable(page, size)));
        return ResponseEntity.ok(PaginationUtils.convertToAppPage(accounts, account -> GenericConvert.convertModelMapper(account, AccountResponse.class)));
    }

}
