package br.com.felipemira.application.core.ports.in;

import br.com.felipemira.application.core.domain.model.Transfer;
import jakarta.validation.Valid;

import static br.com.felipemira.common.validation.Validation.validate;

// Responsavel por definir a porta de entrada (driver) de operacoes para caso de uso de transferencia.
public interface TransferUseCase {

    void transfer(TransferCommand transferCommand);

    record TransferCommand(@Valid Transfer transfer) {

        public TransferCommand(
                Transfer transfer) {
            this.transfer = transfer;
            validate(this);
        }

        public void process(Transfer transfer) {
            transfer.debit.debitValue(transfer.getValue());
            transfer.credit.creditValue(transfer.getValue());
        }
    }
}