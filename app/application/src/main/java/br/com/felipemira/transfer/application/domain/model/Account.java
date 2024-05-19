package br.com.felipemira.transfer.application.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.felipemira.transfer.application.exceptions.Error.*;
import static java.util.Objects.isNull;

// Responsavel por representar a entidade conta e suas regras.
// Nao sera gerenciado pelo IoC e sim pelo repositorio.
@AllArgsConstructor
@ToString
@Builder(setterPrefix = "with")
public class Account {

    @NotNull(message = "deve ser informado")
    @Min(value = 1, message = "deve ser maior que zero")
    @Getter@Setter
    private Long number;
    @Getter@Setter
    private BigDecimal balance;
    @Getter@Setter
    private AccountHolder accountHolder;
    @Getter@Setter
    private Integer active;
    @Getter@Setter
    private LocalDate date;
    @Getter@Setter
    private BigDecimal dailyLimit;

    public Account() {
        number = 0L;
        balance = BigDecimal.ZERO;
        accountHolder = new AccountHolder();
        date = LocalDate.now();
        dailyLimit = new BigDecimal(1000);
    }

    public Account(Long number) {
        this.number = number;
    }

    public void checkActiveAccount(){
        if(this.getActive() == 0){
            inactive(this.getNumber());
        }
    }

    public void dateVerify(){
        if(!isNull(date)){
            if(date.isBefore(LocalDate.now())){
                date = LocalDate.now();
                updateDiaryLimit();
            }
        }
    }

    private void updateDiaryLimit() {
        dailyLimit = new BigDecimal(1000);
    }

    public void creditValue(BigDecimal credito) {
        if (isNull(credito)) {
            mandatory("Valor credito");
        }
        if (credito.compareTo(BigDecimal.ZERO) <= 0) {
            mandatory("Valor credito");
        }
        balance = balance.add(credito);
    }

    public void debitValue(BigDecimal debito) {
        if (isNull(debito)) {
            mandatory("Valor debito");
        }
        if(debito.compareTo(dailyLimit) > 0){
            aboveDailyLimit(dailyLimit);
        }
        if (debito.compareTo(BigDecimal.ZERO) <= 0) {
            mandatory("Valor debito");
        }
        if (debito.compareTo(balance) > 0) {
            insufficientFunds();
        }
        balance = balance.subtract(debito);
        dailyLimit = dailyLimit.subtract(debito);
    }
}
