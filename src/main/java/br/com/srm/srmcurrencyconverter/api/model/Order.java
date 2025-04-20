package br.com.srm.srmcurrencyconverter.api.model;

import br.com.srm.srmcurrencyconverter.api.model.enums.OrderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id()
    @Column(name = "order_id")
    private Integer orderId;
    @ManyToOne()
    @JoinColumn(name = "destiny_currency_id")
    private Currency destinyCurrency;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

}
