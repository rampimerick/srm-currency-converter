package br.com.srm.srmcurrencyconverter.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductKingdomOrderDto {

    @NotNull
    @JsonProperty("productKingdomId")
    private Integer productKingdomId;
    @Min(1)
    @JsonProperty("quantity")
    private Integer quantity;
}
