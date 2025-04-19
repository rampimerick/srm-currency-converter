package br.com.srm.srmcurrencyconverter.api.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class CurrencyRate {

    private final Integer originCurrencyId;
    private final Integer  destinyCurrencyId;
    private final LocalDate date;
    private final BigDecimal conversionRate;

    @JsonCreator
    public CurrencyRate(@JsonProperty("originCurrencyId") @NotNull @Min(1) Integer originCurrencyId,
                        @JsonProperty("destinyCurrencyId") @NotNull @Min(1) Integer destinyCurrencyId,
                        @JsonProperty("date") @NotNull LocalDate date,
                        @JsonProperty("conversionRate") @NotNull @Positive BigDecimal conversionRate) {
        this.originCurrencyId = originCurrencyId;
        this.destinyCurrencyId = destinyCurrencyId;
        this.date = date;
        this.conversionRate = conversionRate;
    }
}
