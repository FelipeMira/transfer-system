package br.com.felipemira.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="conta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(setterPrefix = "with")
@Setter
@ToString
public class AccountEntity implements Serializable {

    @Id
    @Column(name="numero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @Column(name="saldo")
    private BigDecimal balance;

    @Embedded
    private AbstractMainEntity mainEntity;

    @Column(name = "ativa")
    private Integer active;

    @Column(name = "data_atual")
    private LocalDate date;

    @Column(name="limite")
    private BigDecimal dailyLimit;

}
