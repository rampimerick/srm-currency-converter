package br.com.srm.srmcurrencyconverter.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductKingdomDto {

    @JsonProperty(value = "productId")
    @NotNull(message = "O ID do produto é obrigatório")
    private Integer productId;

    @JsonProperty(value = "kingdomId")
    @NotNull(message = "O ID do reino é obrigatório")
    private Integer kingdomId;

    @JsonProperty(value = "value")
    @NotNull(message = "O Valor do produto é obrigatório")
    @Positive(message = "O valor do produto precisa ser maior que zero")
    private BigDecimal value;

    @JsonProperty(value = "productConversionRate")
    @NotNull
    @PositiveOrZero(message = "O valor de conversão específico precisa ser maior ou igual a zero")
    private BigDecimal productConversionRate;

    @JsonProperty(value = "originCurrencyId")
    @NotNull(message = "O ID da moeda de origem é obrigatório")
    private Integer originCurrencyId;


}
