package br.com.srm.srmcurrencyconverter.api.model;

import lombok.AllArgsConstructor;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productOrderId;
    @Column(name = "order_id")
    private Integer orderId;
    @ManyToOne()
    @JoinColumn(name = "product_kingdom_id")
    private ProductKingdom product;
    @Column(name = "amount")
    private Integer amount;

    public ProductOrder(Integer orderId, ProductKingdom product, Integer quantity) {
        this.orderId = orderId;
        this.product = product;
        this.amount = quantity;
    }
}
