package br.com.srm.srmcurrencyconverter.api.model;

import br.com.srm.srmcurrencyconverter.api.model.fk.ProductValuePrimaryKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductValue {

    @EmbeddedId
    private ProductValuePrimaryKey primaryKey;
    private Integer originCurrencyId;
    private BigDecimal value;
}
