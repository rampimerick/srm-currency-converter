package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Currency {

    @Id
    @Column(name = "currency_id")
    private Integer currencyId;
    @Column()
    private String name;
    @Column(name = "base_value")
    private BigDecimal baseValue;

}
