package br.com.srm.srmcurrencyconverter.api.dto.response;

import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.Kingdom;
import br.com.srm.srmcurrencyconverter.api.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDto {

    private Integer productKingdomId;
    private Kingdom kingdom;
    private Product product;
    private Currency originCurrency;
    private BigDecimal value;
    private Integer amount;

}
