package br.com.srm.srmcurrencyconverter.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRateDto {

    @JsonProperty("originCurrencyId")
    @NotNull(message = "Origin currency is required")
    @Min(value = 1, message = "Origin currency must be a valid ID greater than 0")
    private Integer originCurrencyId;

    @JsonProperty("destinyCurrencyId")
    @NotNull(message = "Destination currency is required")
    @Min(value = 1, message = "Destination currency must be a valid ID greater than 0")
    private Integer destinyCurrencyId;

    @JsonProperty("date")
    @NotNull(message = "Date is required")
    private LocalDate date;

    @JsonProperty("conversionRate")
    @NotNull(message = "Conversion rate is required")
    @Positive(message = "Conversion rate must be greater than zero")
    private BigDecimal conversionRate;

}
