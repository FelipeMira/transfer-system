package br.com.felipemira.database.interfaces;

import br.com.felipemira.database.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountCrudRepository extends JpaRepository<AccountEntity, Integer> {

}
