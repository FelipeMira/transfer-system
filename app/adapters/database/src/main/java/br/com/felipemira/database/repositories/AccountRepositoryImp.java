package br.com.felipemira.database.repositories;

import br.com.felipemira.database.entities.AccountEntity;
import br.com.felipemira.database.interfaces.AccountCrudRepository;
import br.com.felipemira.database.map.GenericConvert;
import br.com.felipemira.transfer.application.domain.model.Account;
import br.com.felipemira.transfer.application.exceptions.BusinessException;
import br.com.felipemira.transfer.application.ports.out.AccountPort;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Objects.isNull;

// Responsavel por implementar a porta de saida (driven) de servicos de banco de dados usando spring jdbc
@Named
public class AccountRepositoryImp implements AccountPort {

    private static final String ERROR = "Erro inesperado de acesso ao banco. Entre em contato com adminstrador.";

    private final AccountCrudRepository accountCrudRepository;


    @Inject
    public AccountRepositoryImp(AccountCrudRepository accountCrudRepository) {
        this.accountCrudRepository = accountCrudRepository;
    }
    @Override
    public Account getAccount(Long numero) {
        if (isNull(numero)) {
            return null;
        }
        try{
            Optional<AccountEntity> optionalContaEntity = accountCrudRepository.findById(Math.toIntExact(numero));

            return GenericConvert.convertModelMapper(optionalContaEntity.isEmpty()? null : optionalContaEntity.get(), Account.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ERROR);
        }
    }

    @Transactional
    @Override
    public void updateAccount(Account conta) {
        if (isNull(conta)) {
            throw new BusinessException("Conta e obrigatorio.");
        }
        try {
            accountCrudRepository.saveAndFlush(GenericConvert.convertModelMapper(conta, AccountEntity.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ERROR);
        }
    }
}

