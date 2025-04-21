package br.com.srm.srmcurrencyconverter.api.dto.request;

import br.com.srm.srmcurrencyconverter.api.model.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewOrderDto {

    @NotNull(message = "Destiny currency is required")
    @Min(1)
    @JsonProperty("destinyCurrencyId")
    private Integer destinyCurrencyId;
    @NotNull(message = "Order date is required")
    @JsonProperty("orderDate")
    private LocalDate orderDate;
    @NotNull(message = "Order type is required")
    @JsonProperty("orderType")
    private OrderType orderType;
    @NotEmpty(message = "Products are required")
    @JsonProperty("products")
    private List<ProductKingdomOrderDto> products;

}
