package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Kingdom {

    private Integer kingdomId;
    private String name;
    private String race;
    private String specialty;
}
