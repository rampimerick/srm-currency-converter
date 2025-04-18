package br.com.srm.srmcurrencyconverter.api.model.enums;

public enum TransactionType {

    PURCHASE(1, "PURCHASE"), SALE (2, "SALE") {
    };
    
    private Integer transactionTypeId;
    private String transactionTypeName;

    TransactionType(int transactionTypeId, String transactionTypeName) {
    }
}
