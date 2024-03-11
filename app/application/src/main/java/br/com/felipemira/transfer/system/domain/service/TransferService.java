package br.com.felipemira.transfer.system.domain.service;

import br.com.felipemira.transfer.common.UseCase;
import br.com.felipemira.transfer.system.domain.model.TransactionBacen;
import br.com.felipemira.transfer.system.domain.model.Transfer;
import br.com.felipemira.transfer.system.ports.in.TransferUseCase;
import br.com.felipemira.transfer.system.ports.out.AccountPort;
import br.com.felipemira.transfer.system.ports.out.BacenPort;
import br.com.felipemira.transfer.system.ports.out.RegisterPort;
import jakarta.inject.Inject;

import static br.com.felipemira.transfer.system.exceptions.Error.*;
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

        //2. validação de contas
        var debit = accountPort.getAccount(transferCommand.transfer().getDebit().getNumber());

        if (isNull(debit)) {
            nonexistent("Conta debito");
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

        if (isNull(credit)) {
            nonexistent("Conta credito");
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
