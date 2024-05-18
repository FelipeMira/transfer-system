package br.com.felipemira.transfer.application.exceptions;

import java.math.BigDecimal;

// Responsavel por centralizar todas as mensagens de validacoes.
public class Error {
    // erros genericos
    public static void mandatory(String value) {
        throw new BusinessException(value + " e obrigatorio.");
    }

    public static void nonexistent(String value) {
        throw new BusinessException(value + " e inexistente.");
    }

    public static void accountHolderNonexistent
            (Long idAccountHolder) {
        throw new BusinessException("O correntista com id " + idAccountHolder + " e inexistente.");
    }

    public static void inactive(Long idAccount) {
        throw new BusinessException("A conta " + idAccount + " esta inativa.");
    }

    // erros especificos
    public static void insufficientFunds() {
        throw new BusinessException("Saldo insuficiente.");
    }

    public static void sameAccount() {
        throw new BusinessException("Conta debito e credito devem ser diferentes.");
    }

    public static void dateNotCurrent() {
        throw new BusinessException("Data deve ser do dia atual.");
    }

    public static void aboveDailyLimit(BigDecimal limit) {
        throw new BusinessException("Transacao acima do limite diario restante: R$" + limit + " .");
    }
}
