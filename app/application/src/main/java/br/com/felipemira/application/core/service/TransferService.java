package br.com.felipemira.application.core.service;

import br.com.felipemira.application.core.domain.model.TransactionBacen;
import br.com.felipemira.application.core.domain.model.Transfer;
import br.com.felipemira.application.core.ports.in.TransferUseCase;
import br.com.felipemira.application.core.ports.out.AccountPort;
import br.com.felipemira.application.core.ports.out.BacenPort;
import br.com.felipemira.application.core.ports.out.RegisterPort;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

import static br.com.felipemira.application.core.exceptions.MessagesException.*;
import static java.util.Objects.isNull;

@Named
public class TransferService implements TransferUseCase {

    private final AccountPort accountPort;

    private final RegisterPort registerPort;

    private final BacenPort bacenPort;

    @Inject
    public TransferService(AccountPort accountPort, RegisterPort registerPort, BacenPort bacenPort) {
        this.accountPort = accountPort;
        this.registerPort = registerPort;
        this.bacenPort = bacenPort;
    }

    @Override
    public void transfer(TransferCommand transferCommand) {
        //1. validação de parametros sera feita pelo SelfValidation

        //2. validacao de contas
        var debit = accountPort.getAccount(transferCommand.transfer().getDebit().getNumber());

        if(Objects.isNull(debit)){
            accountNonexistent(transferCommand.transfer().getDebit().getNumber());
        }

        if(debit.getActive() == 0){
            inactive(debit.getNumber());
        }

        var debitAccountHolder = registerPort.getAccountHolder(debit.getAccountHolder().getIdAccountHolder());

        if(isNull(debitAccountHolder)){
            accountHolderNonexistent(debit.getAccountHolder().getIdAccountHolder());
        }

        debit.setAccountHolder(debitAccountHolder);

        var credit = accountPort.getAccount(transferCommand.transfer().getCredit().getNumber());

        if(Objects.isNull(credit)){
            accountNonexistent(transferCommand.transfer().getDebit().getNumber());
        }

        if(credit.getActive() == 0){
            inactive(credit.getNumber());
        }

        var creditAccountHolder = registerPort.getAccountHolder(credit.getAccountHolder().getIdAccountHolder());

        if(isNull(creditAccountHolder)){
            accountHolderNonexistent(credit.getAccountHolder().getIdAccountHolder());
        }

        credit.setAccountHolder(creditAccountHolder);

        //3. validacao limite diario
        debit.dateVerify();

        //4. operação
        transferCommand.process(new Transfer(debit, credit, transferCommand.transfer().getValue()));
        accountPort.updateAccount(debit);
        accountPort.updateAccount(credit);

        //6. registro da operação no BACEN
        bacenPort.postTransactionBacen(new TransactionBacen(debit.getNumber(), credit.getNumber(), transferCommand.transfer().getValue()));
    }
}
