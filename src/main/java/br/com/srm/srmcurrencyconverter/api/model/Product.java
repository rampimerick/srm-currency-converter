package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String description;
    @ManyToOne
    private ProductValue productValue;
    private BigDecimal conversionRate;
    private LocalDate date;

}
