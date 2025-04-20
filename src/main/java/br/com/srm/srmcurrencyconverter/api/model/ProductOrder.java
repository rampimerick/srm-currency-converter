package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products_order")
public class ProductOrder {

    @Id
    @Column(name = "products_order_id")
    private Integer productOrderId;
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne()
    @JoinColumn(name = "product_kingdom_id")
    private ProductKingdom productId;
    @Column(name = "product_quantity")
    private Integer quantity;
}
