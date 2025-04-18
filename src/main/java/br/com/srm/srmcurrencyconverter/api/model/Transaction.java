package br.com.srm.srmcurrencyconverter.api.model;

import br.com.srm.srmcurrencyconverter.api.model.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    private Integer transactionId;
    @OneToMany
    private List<Product> products;
    private BigDecimal totalValue;
    @ManyToOne
    private Currency originCurrency;
    @ManyToOne
    private Currency destinationCurrency;
    private Integer productQuantity;
    private TransactionType transactionType;
}
