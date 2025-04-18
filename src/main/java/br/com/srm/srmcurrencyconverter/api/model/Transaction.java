package br.com.srm.srmcurrencyconverter.api.model;

import br.com.srm.srmcurrencyconverter.api.model.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    private Integer transactionId;
    private List<Product> products;
    private BigDecimal totalValue;
    private Currency originCurrency;
    private Currency destinationCurrency;
    private Integer productQuantity;
    private TransactionType transactionType;
}
