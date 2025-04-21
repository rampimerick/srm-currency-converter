package br.com.srm.srmcurrencyconverter.api.dto.response;

import br.com.srm.srmcurrencyconverter.api.dto.request.ProductKingdomDto;
import br.com.srm.srmcurrencyconverter.api.model.Currency;
import br.com.srm.srmcurrencyconverter.api.model.Kingdom;
import br.com.srm.srmcurrencyconverter.api.model.Product;
import br.com.srm.srmcurrencyconverter.api.model.ProductKingdom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ConvertedProductDto {

    private Integer productKingdomId;
    private Kingdom kingdom;
    private Product product;
    private BigDecimal convertedValue;


    public ConvertedProductDto(ProductKingdom productKingdom, BigDecimal convertedValue) {
        this.productKingdomId = productKingdom.getProductKingdomId();
        this.kingdom = productKingdom.getKingdom();
        this.product = productKingdom.getProduct();
        this.convertedValue = convertedValue;
    }
}
