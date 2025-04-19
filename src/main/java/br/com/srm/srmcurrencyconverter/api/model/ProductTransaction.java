package br.com.srm.srmcurrencyconverter.api.model;

import br.com.srm.srmcurrencyconverter.api.model.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_transactions")
public class ProductTransaction {

    @Id()
    @Column(name = "transaction_id")
    private Integer transactionId;
    @ManyToOne()
    @JoinColumn(name = "product_kingdom_id")
    private ProductByKingdom productKingdom;
    @ManyToOne()
    @JoinColumn(name = "destiny_currency_id")
    private Currency destinyCurrency;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column(name = "product_quantity")
    private Integer productQuantity;

}
