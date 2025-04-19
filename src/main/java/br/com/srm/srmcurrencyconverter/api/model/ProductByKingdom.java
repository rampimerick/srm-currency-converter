package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products_by_kingdoms")
public class ProductByKingdom {

    @Id()
    @Column(name = "product_kingdom_id")
    private Integer productKingdomId ;
    @ManyToOne()
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_id"))
    private Product product;
    @ManyToOne()
    @JoinColumn(name = "kingdom_id", foreignKey = @ForeignKey(name = "kingdom_id"))
    private Kingdom kingdom;
    @Column()
    private BigDecimal value;
    @ManyToOne()
    @JoinColumn(name = "origin_currency_id")
    private Currency originCurrency;
    @Column()
    private BigDecimal productConversionRate;
}
