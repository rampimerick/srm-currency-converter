package br.com.srm.srmcurrencyconverter.api.model.fk;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ProductValuePrimaryKey implements Serializable {

    private static final long serialVersionUID = 4017350073357302334L;
    private Integer productId;
    private Integer kingdomId;
}
