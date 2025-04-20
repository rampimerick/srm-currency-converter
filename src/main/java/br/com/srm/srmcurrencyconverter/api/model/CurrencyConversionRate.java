package br.com.srm.srmcurrencyconverter.api.model;

import br.com.srm.srmcurrencyconverter.api.dto.request.CurrencyRateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "currency_conversion_rate")
public class CurrencyConversionRate {

    @Id
    @Column(name = "currency_conversion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer currencyConversionId;
    @ManyToOne
    @JoinColumn(name = "origin_currency_id")
    private Currency originCurrency;
    @ManyToOne
    @JoinColumn(name = "destiny_currency_id")
    private Currency  destinyCurrency;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "conversion_rate")
    private BigDecimal conversionRate;


    public CurrencyConversionRate(final CurrencyRateDto currencyRateDto, Currency originCurrency, Currency destinyCurrency) {
        this.originCurrency = originCurrency;
        this.destinyCurrency = destinyCurrency;
        this.date = currencyRateDto.getDate();
        this.conversionRate = currencyRateDto.getConversionRate();
    }

    public CurrencyConversionRate reverseCurrencyRate () {
        CurrencyConversionRate reverseCurrencyRate = new CurrencyConversionRate();
        reverseCurrencyRate.originCurrency = this.destinyCurrency;
        reverseCurrencyRate.destinyCurrency =  this.originCurrency;
        reverseCurrencyRate.date = this.date;
        reverseCurrencyRate.conversionRate = BigDecimal.ONE.divide(this.conversionRate, 2, RoundingMode.HALF_UP);
        return reverseCurrencyRate;
    }
}
