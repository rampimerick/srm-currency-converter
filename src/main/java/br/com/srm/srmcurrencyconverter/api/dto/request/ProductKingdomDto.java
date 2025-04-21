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
    @NotNull(message = "Product ID is required")
    private Integer productId;

    @JsonProperty(value = "kingdomId")
    @NotNull(message = "Kingdom ID is required")
    private Integer kingdomId;

    @JsonProperty(value = "value")
    @NotNull(message = "Product value is required")
    @Positive(message = "Product value must be greater than zero")
    private BigDecimal value;

    @JsonProperty(value = "productConversionRate")
    @NotNull
    @PositiveOrZero(message = "Product conversion rate must be greater than or equal to zero")
    private BigDecimal productConversionRate;

    @JsonProperty(value = "originCurrencyId")
    @NotNull(message = "Origin currency ID is required")
    private Integer originCurrencyId;


}
