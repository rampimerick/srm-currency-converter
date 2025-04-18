package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal value;
    private Kingdom originKingdom;
    private Currency originCurrency;
    private BigDecimal conversionRate;

}
