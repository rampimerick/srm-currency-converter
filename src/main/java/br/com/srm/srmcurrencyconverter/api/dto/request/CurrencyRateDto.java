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
    @NotNull(message = "A moeda de origem é obrigatória")
    @Min(1)
    private Integer originCurrencyId;

    @JsonProperty("destinyCurrencyId")
    @NotNull(message = "A moeda de destino é obrigatória")
    @Min(1)
    private Integer  destinyCurrencyId;

    @JsonProperty("date")
    @NotNull(message = "A data é obrigatória")
    private LocalDate date;

    @JsonProperty("conversionRate")
    @NotNull(message = "A taxa de conversão é obrigatória")
    @Positive(message = "A taxa de conversão deve ser maior que zero")
    private BigDecimal conversionRate;

}
