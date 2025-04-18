package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "currency_conversion_rate")
public class CurrencyConversionRate {

    @Id
    @Column(name = "currency_conversion_id")
    private Integer currencyConversionId;
    @ManyToOne
    @JoinColumn(name = "origin_currency_id")
    private Currency originCurrency;
    @ManyToOne
    @JoinColumn(name = "destiny_currency_id")
    private Currency  destinyCurrency;
    private LocalDate date;
    private BigDecimal conversionRate;
}
