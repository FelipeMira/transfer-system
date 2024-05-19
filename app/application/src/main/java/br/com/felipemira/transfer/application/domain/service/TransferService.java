package br.com.felipemira.transfer.application.domain.service;

import br.com.felipemira.transfer.common.UseCase;
import br.com.felipemira.transfer.application.domain.model.TransactionBacen;
import br.com.felipemira.transfer.application.domain.model.Transfer;
import br.com.felipemira.transfer.application.ports.in.TransferUseCase;
import br.com.felipemira.transfer.application.ports.out.AccountPort;
import br.com.felipemira.transfer.application.ports.out.BacenPort;
import br.com.felipemira.transfer.application.ports.out.RegisterPort;
import jakarta.inject.Inject;

import static br.com.felipemira.transfer.application.exceptions.Error.*;
import static java.util.Objects.isNull;

@UseCase
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

        if(debit.getActive() == 0){
            inactive(debit.getNumber());
        }

        var debitAccountHolder = registerPort.getAccountHolder(debit.getAccountMain().getIdAccount());

        if(isNull(debitAccountHolder)){
            accountHolderNonexistent(debit.getAccountMain().getIdAccount());
        }

        debit.setAccountMain(debitAccountHolder);

        var credit = accountPort.getAccount(transferCommand.transfer().getCredit().getNumber());

        if(credit.getActive() == 0){
            inactive(credit.getNumber());
        }

        var creditAccountHolder = registerPort.getAccountHolder(credit.getAccountMain().getIdAccount());

        if(isNull(creditAccountHolder)){
            accountHolderNonexistent(credit.getAccountMain().getIdAccount());
        }

        credit.setAccountMain(creditAccountHolder);

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
