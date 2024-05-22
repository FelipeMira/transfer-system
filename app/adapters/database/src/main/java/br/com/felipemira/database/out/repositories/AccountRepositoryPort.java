package br.com.felipemira.database.out.repositories;

import br.com.felipemira.application.core.ports.out.AccountPort;
import br.com.felipemira.common.domain.pagination.AppPage;
import br.com.felipemira.common.domain.pagination.AppPageable;
import br.com.felipemira.common.mappers.GenericConvert;
import br.com.felipemira.common.util.pagination.PaginationUtils;
import br.com.felipemira.database.entities.AccountEntity;
import br.com.felipemira.database.interfaces.AccountCrudRepository;
import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.exceptions.BusinessException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

// Responsavel por implementar a porta de saida (driven) de servicos de banco de dados usando spring jdbc
@Component
public class AccountRepositoryPort implements AccountPort {

    private static final String ERROR = "Erro inesperado de acesso ao banco. Entre em contato com adminstrador.";

    private final AccountCrudRepository accountCrudRepository;

    @Inject
    public AccountRepositoryPort(AccountCrudRepository accountCrudRepository) {
        this.accountCrudRepository = accountCrudRepository;
    }

    @Override
    public Account getAccount(Long accountNumber) {
        if(accountNumber == null){
            return null;
        }
        try{
            Optional<AccountEntity> optionalContaEntity = accountCrudRepository.findById(Math.toIntExact(accountNumber));

            return GenericConvert.convertModelMapper(optionalContaEntity.isEmpty()? null : optionalContaEntity.get(), Account.class);
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            throw new BusinessException(ERROR);
        }
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        if(account == null){
            throw new BusinessException("Conta e obrigatorio.");
        }
        try {
            accountCrudRepository.save(GenericConvert.convertModelMapper(account, AccountEntity.class));
        }catch(Exception ex){
            //noinspection CallToPrintStackTrace
            ex.printStackTrace();
            throw new BusinessException(ERROR);
        }
    }

    @Override
    public AppPage<Account> fetchAccounts(AppPageable appPageable) {
        Pageable springPageable = PaginationUtils.convertToPageable(appPageable);
        Page<AccountEntity> accountEntities = accountCrudRepository.findAll(springPageable);
        List<Account> accounts = accountEntities.stream()
                .map(entity -> GenericConvert.convertModelMapper(entity, Account.class))
                .collect(Collectors.toList());
        Page<Account> accountPage = new PageImpl<>(accounts, springPageable, accountEntities.getTotalElements());
        return PaginationUtils.convertToAppPage(accountPage, Function.identity());
    }
}

